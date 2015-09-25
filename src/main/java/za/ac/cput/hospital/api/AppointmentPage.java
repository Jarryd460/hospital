package za.ac.cput.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.hospital.conf.factory.AppointmentFactory;
import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Invoice;
import za.ac.cput.hospital.model.AppointmentResource;
import za.ac.cput.hospital.services.AppointmentService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
@RestController
@RequestMapping(value="/app/**")
public class AppointmentPage {

    @Autowired
    private AppointmentService service;

    //@RequestMapping(value="/appointments", method= RequestMethod.GET)
    //public List<AppointmentResource> getAppointments() {
        //Appointment appointment1 = AppointmentFactory.createAppointment(new Date(), "Checkup", new BigDecimal(10000), null);
        //service.create(appointment1);
        //List<AppointmentResource> hateos = new ArrayList<AppointmentResource>();
        //List<Appointment> appointments = service.findAll();
        //for (Appointment appointment : appointments) {
            //AppointmentResource res = new AppointmentResource
                    //.Builder(appointment.getDate())
                    //.description(appointment.getDescription())
                    //.amount(appointment.getAmount())
                    //.invoiceList(appointment.getInvoiceList())
                    //.resid(appointment.getId())
                   //.build();
           // Link appointmentsLink = new
                   // Link("http://localhost:8080/appointment/"+res.getResId().toString())
                   // .withRel("appointments");
            //res.add(appointmentsLink);
            //hateos.add(res);
        //}
        //return hateos;
   //}

    @RequestMapping(value="/invoice/{id}", method= RequestMethod.GET)
    public List<Invoice> getInvoices(@PathVariable Long id) {
        return service.getInvoices(id);
    }

    @RequestMapping(value = "/appointments/", method = RequestMethod.GET)
    public ResponseEntity<List<Appointment>> listAllAppointments() {
        List<Appointment> appointmentList = service.findAll();
        if(appointmentList.isEmpty()){
            return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Appointment>>(appointmentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/appointment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> getAppointment(@PathVariable("id") long id) {
        System.out.println("Fetching Appointment with id " + id);
        Appointment appointment = service.findById(id);
        if (appointment == null) {
            System.out.println("Appointment with id " + id + " not found");
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
    }

    @RequestMapping(value = "/appointment/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createAppointment(@RequestBody Appointment appointment,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Appointment " + appointment.getId());

//     USE THIS IF YOU WANT TO CHECK UNIQUE OBJECT
//      if (SubjectService.isSubjectExist(Subject)) {
//            System.out.println("A Subject with name " + Subject.getName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }

        service.create(appointment);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/appointment/{id}").buildAndExpand(appointment.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/appointment/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") long id, @RequestBody Appointment appointment) {
        System.out.println("Updating Appointment " + id);

        Appointment currentAppointment = service.findById(id);

        if (currentAppointment==null) {
            System.out.println("Appointment with id " + id + " not found");
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        }

        Appointment updatedAppointment = new Appointment
                .Builder(currentAppointment.getDate())
                .copy(currentAppointment)
                .id(appointment.getId())
                .amount(appointment.getAmount())
                .date(appointment.getDate())
                .description(appointment.getDescription())
                .invoiceList(appointment.getInvoiceList())
                .build();
        service.edit(updatedAppointment);
        return new ResponseEntity<Appointment>(updatedAppointment, HttpStatus.OK);
    }

    @RequestMapping(value = "/appointment/delete/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable("id") long id, @RequestBody Appointment app) {
        System.out.println("Fetching & Deleting Appointment with id " + id);

        Appointment appointment = service.findById(id);
        if (appointment == null) {
            System.out.println("Unable to delete. Appointment with id " + id + " not found");
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        }

        service.delete(appointment);
        return new ResponseEntity<Appointment>(HttpStatus.OK);
    }

}
