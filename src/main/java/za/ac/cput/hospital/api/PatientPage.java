package za.ac.cput.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Patient;
import za.ac.cput.hospital.model.PatientResource;
import za.ac.cput.hospital.services.PatientService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
@RestController
@RequestMapping(value="/patient/**")
public class PatientPage {

    @Autowired
    private PatientService service;

    @RequestMapping(value="/patients", method= RequestMethod.GET)
    public List<PatientResource> getPatients() {
        List<PatientResource> hateos = new ArrayList<PatientResource>();
        List<Patient> patients = service.getPatients();
        for (Patient patient : patients) {
            PatientResource res = new PatientResource
                    .Builder(patient.getName())
                    .demographic(patient.getDemographic())
                    .contact(patient.getContact())
                    .address(patient.getAddress())
                    .appointmentList(patient.getAppointmentList())
                    .resid(patient.getId())
                    .build();
            Link patientsLink = new
                    Link("http://localhost:8080/patient/"+res.getResId().toString())
                    .withRel("patients");
            res.add(patientsLink);
            hateos.add(res);
        }
        return hateos;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public List<Appointment> getAppointments(@PathVariable Long id) {
        return service.getAppointments(id);
    }

}
