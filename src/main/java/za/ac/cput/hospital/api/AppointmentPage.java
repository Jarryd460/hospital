package za.ac.cput.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Invoice;
import za.ac.cput.hospital.model.AppointmentResource;
import za.ac.cput.hospital.services.AppointmentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
@RestController
@RequestMapping(value="/appointment/**")
public class AppointmentPage {

    @Autowired
    private AppointmentService service;

    @RequestMapping(value="/appointments", method= RequestMethod.GET)
    public List<AppointmentResource> getAppointments() {
        List<AppointmentResource> hateos = new ArrayList<AppointmentResource>();
        List<Appointment> appointments = service.getAppointments();
        for (Appointment appointment : appointments) {
            AppointmentResource res = new AppointmentResource
                    .Builder(appointment.getDate())
                    .description(appointment.getDescription())
                    .amount(appointment.getAmount())
                    .invoiceList(appointment.getInvoiceList())
                    .build();
            Link appointmentsLink = new
                    Link("http://localhost:8080/appointment/"+res.getResId().toString())
                    .withRel("appointments");
            res.add(appointmentsLink);
            hateos.add(res);
        }
        return hateos;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public List<Invoice> getInvoices(@PathVariable Long id) {
        return service.getInvoices(id);
    }

}
