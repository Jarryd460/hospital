package za.ac.cput.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.InvoiceFactory;
import za.ac.cput.hospital.domain.Invoice;
import za.ac.cput.hospital.repository.InvoiceRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/06/26.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class InvoiceServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private InvoiceService service;
    private Long id;
    @Autowired
    private InvoiceRepository repository;

    @Test
    public void create() throws Exception {
        repository.deleteAll();
        Invoice invoice1 = InvoiceFactory.createInvoice(new Date(), new BigDecimal(20000));
        repository.save(invoice1);
        id=invoice1.getId();
        Assert.assertNotNull(invoice1.getId());
    }

    @Test(dependsOnMethods = "create")
    public void testGetInvoice() throws Exception {
        Invoice invoice = service.findById(id);
        Assert.assertNotNull(invoice.getId());
    }

    @Test(dependsOnMethods = "testGetInvoice")
    public void testGetInvoices() throws Exception {
        List<Invoice> invoiceList = service.findAll();
        Assert.assertEquals(invoiceList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetInvoice")
    public void testEditInvoice() throws Exception {
        Invoice invoice = repository.findOne(id);
        Invoice updatedInvoice = new Invoice.Builder(invoice.getAmount()).copy(invoice).amount(new BigDecimal(20)).build();
        service.edit(updatedInvoice);
        Invoice newInvoice = repository.findOne(id);
        Assert.assertNotNull(newInvoice.getId());
    }

    @Test(dependsOnMethods = "testEditInvoice")
    public void testDeleteInvoice() throws Exception {
        Invoice invoice = repository.findOne(id);
        service.delete(invoice);
        Invoice newInvoice = repository.findOne(id);
        Assert.assertNull(newInvoice);
    }

}
