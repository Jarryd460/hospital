package za.ac.cput.hospital.services;

import za.ac.cput.hospital.domain.Invoice;

import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
public interface InvoiceService {

    Invoice getInvoice(Long id);

    List<Invoice> getInvoices();

    void create(Invoice invoice);

    void edit(Invoice invoice);

    void delete(Invoice invoice);

}
