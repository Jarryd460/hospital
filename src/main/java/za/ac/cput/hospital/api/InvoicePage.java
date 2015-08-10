package za.ac.cput.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.hospital.domain.Invoice;
import za.ac.cput.hospital.model.InvoiceResource;
import za.ac.cput.hospital.services.InvoiceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
@RestController
@RequestMapping(value="/invoice/**")
public class InvoicePage {

    @Autowired
    private InvoiceService service;

    @RequestMapping(value="/invoices", method= RequestMethod.GET)
    public List<InvoiceResource> getInvoices() {
        List<InvoiceResource> hateos = new ArrayList<InvoiceResource>();
        List<Invoice> invoices = service.getInvoices();
        for (Invoice invoice : invoices) {
            InvoiceResource res = new InvoiceResource
                    .Builder(invoice.getAmount())
                    .date(invoice.getDate())
                    .resid(invoice.getId())
                    .build();
            Link invoicesLink = new
                    Link("http://localhost:8080/invoice/"+res.getResId().toString())
                    .withRel("invoices");
            res.add(invoicesLink);
            hateos.add(res);
        }
        return hateos;
    }

}
