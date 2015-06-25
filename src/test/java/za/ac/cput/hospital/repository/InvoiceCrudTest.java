package za.ac.cput.hospital.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.InvoiceFactory;
import za.ac.cput.hospital.domain.Invoice;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by student on 2015/06/25.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class InvoiceCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    InvoiceRepository repository;

    @Test
    public void testCreate() throws Exception {
        Date date = new Date();
        Invoice invoice = InvoiceFactory.createInvoice(date, null, new BigDecimal(12000));
        repository.save(invoice);
        id = invoice.getId();
        Assert.assertNotNull(invoice.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        Invoice invoice = repository.findOne(id);
        DecimalFormat df = new DecimalFormat("#.00");
        Assert.assertEquals(df.format(invoice.getAmount()), df.format(12000));
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        Invoice invoice = repository.findOne(id);
        Invoice newInvoice = new Invoice.Builder(invoice.getAmount()).copy(invoice).amount(new BigDecimal(10000)).build();
        repository.save(newInvoice);
        Invoice updatedInvoice = repository.findOne(id);
        DecimalFormat df = new DecimalFormat("#.00");
        Assert.assertEquals(df.format(updatedInvoice.getAmount()), df.format(10000));
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Invoice invoice = repository.findOne(id);
        repository.delete(invoice);
        Invoice newInvoice = repository.findOne(id);
        Assert.assertNull(newInvoice);
    }
}
