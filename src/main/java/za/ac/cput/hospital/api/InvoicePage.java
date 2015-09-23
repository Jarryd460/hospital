package za.ac.cput.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.hospital.domain.Invoice;
import za.ac.cput.hospital.model.InvoiceResource;
import za.ac.cput.hospital.services.InvoiceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
@RestController
@RequestMapping(value="/inv/**")
public class InvoicePage {

    @Autowired
    private InvoiceService service;

    //@RequestMapping(value="/invoices", method= RequestMethod.GET)
    //public List<InvoiceResource> getInvoices() {
        //List<InvoiceResource> hateos = new ArrayList<InvoiceResource>();
        //List<Invoice> invoices = service.findAll();
        //for (Invoice invoice : invoices) {
            //InvoiceResource res = new InvoiceResource
                    //.Builder(invoice.getAmount())
                    //.date(invoice.getDate())
                    //.resid(invoice.getId())
                    //.build();
            //Link invoicesLink = new
                    //Link("http://localhost:8080/invoice/"+res.getResId().toString())
                    //.withRel("invoices");
            //res.add(invoicesLink);
            //hateos.add(res);
        //}
        //return hateos;
    //}

    @RequestMapping(value = "/invoices/", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> listAllInvoices() {
        List<Invoice> invoiceList = service.findAll();
        if(invoiceList.isEmpty()){
            return new ResponseEntity<List<Invoice>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Invoice>>(invoiceList, HttpStatus.OK);
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") long id) {
        System.out.println("Fetching Invoice with id " + id);
        Invoice invoice = service.findById(id);
        if (invoice == null) {
            System.out.println("Invoice with id " + id + " not found");
            return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
    }

    @RequestMapping(value = "/invoice/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createInvoice(@RequestBody Invoice invoice,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Invoice " + invoice.getId());

//     USE THIS IF YOU WANT TO CHECK UNIQUE OBJECT
//      if (SubjectService.isSubjectExist(Subject)) {
//            System.out.println("A Subject with name " + Subject.getName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }

        service.create(invoice);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/invoice/{id}").buildAndExpand(invoice.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/invoice/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
        System.out.println("Updating Invoice " + id);

        Invoice currentInvoice = service.findById(id);

        if (currentInvoice==null) {
            System.out.println("Invoice with id " + id + " not found");
            return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
        }

        Invoice updatedInvoice = new Invoice
                .Builder(currentInvoice.getAmount())
                .copy(currentInvoice)
                .build();
        service.edit(currentInvoice);
        return new ResponseEntity<Invoice>(updatedInvoice, HttpStatus.OK);
    }

    @RequestMapping(value = "/invoice/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Invoice with id " + id);

        Invoice invoice = service.findById(id);
        if (invoice == null) {
            System.out.println("Unable to delete. Invoice with id " + id + " not found");
            return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
        }

        service.delete(invoice);
        return new ResponseEntity<Invoice>(HttpStatus.NO_CONTENT);
    }

}
