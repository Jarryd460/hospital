package za.ac.cput.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Doctor;
import za.ac.cput.hospital.model.DoctorResource;
import za.ac.cput.hospital.services.DoctorService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
@RestController
@RequestMapping(value="/doctor/**")
public class DoctorPage {

    @Autowired
    private DoctorService service;

    @RequestMapping(value="/doctors", method= RequestMethod.GET)
    public List<DoctorResource> getDoctors() {
        List<DoctorResource> hateos = new ArrayList<DoctorResource>();
        List<Doctor> doctors = service.getDoctors();
        for (Doctor doctor : doctors) {
            DoctorResource res = new DoctorResource
                    .Builder(doctor.getName())
                    .demographic(doctor.getDemographic())
                    .contact(doctor.getContact())
                    .address(doctor.getAddress())
                    .specialization(doctor.getSpecialization())
                    .appointmentList(doctor.getAppointmentList())
                    .resid(doctor.getId())
                    .build();
            Link doctorsLink = new
                    Link("http://localhost:8080/doctor/"+res.getResId().toString())
                    .withRel("doctors");
            res.add(doctorsLink);
            hateos.add(res);
        }
        return hateos;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public List<Appointment> getAppointments(@PathVariable Long id) {
        return service.getAppointments(id);
    }

}
