package za.ac.cput.hospital.conf.factory;

import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Invoice;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by student on 2015/06/23.
 */
public class InvoiceFactory {

    public static Invoice createInvoice(Date date, BigDecimal amount) {
        return new Invoice.Builder(amount).date(date).build();
    }

}
