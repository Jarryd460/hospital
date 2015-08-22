package za.ac.cput.hospital.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.hospital.domain.Invoice;
import za.ac.cput.hospital.repository.InvoiceRepository;
import za.ac.cput.hospital.services.InvoiceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    InvoiceRepository repository;

    @Override
    public Invoice getInvoice(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Invoice> getInvoices() {
        List<Invoice> allInvoices = new ArrayList<Invoice>();

        Iterable<Invoice> invoices = repository.findAll();
        for (Invoice invoice : invoices) {
            allInvoices.add(invoice);
        }
        return allInvoices;
    }

    @Override
    public void create(Invoice invoice) {
        repository.save(invoice);
    }

    @Override
    public void edit(Invoice invoice) {
        Invoice updatedInvoice = new Invoice.Builder(invoice.getAmount()).copy(invoice).date(invoice.getDate()).build();
        repository.save(updatedInvoice);
    }

    @Override
    public void delete(Invoice invoice) {
        repository.delete(invoice);
    }

}
