package za.ac.cput.hospital.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.hospital.conf.factory.InvoiceFactory;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by student on 2015/06/25.
 */
public class InvoiceTest {

    @Test
    public void testCreate() {
        Invoice invoice = InvoiceFactory.createInvoice("04-29-1992", new BigDecimal(12000));
        Assert.assertEquals(invoice.getDate(), "04-29-1992");
        Assert.assertEquals(invoice.getAmount(), new BigDecimal(12000));
    }

    @Test
    public void testUpdate() {
        Invoice invoice = InvoiceFactory.createInvoice("04-29-1992", new BigDecimal(12000));
        Invoice invoiceCopy = new Invoice.Builder(invoice.getAmount()).copy(invoice).amount(new BigDecimal(10000)).build();
        Assert.assertEquals(invoiceCopy.getDate(), "04-29-1992");
        Assert.assertEquals(invoiceCopy.getAmount(), new BigDecimal(10000));
    }

}
