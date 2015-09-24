package za.ac.cput.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
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
@RequestMapping(value="/doc/**")
public class DoctorPage {

    @Autowired
    private DoctorService service;

    //@RequestMapping(value="/doctors", method= RequestMethod.GET)
    //public List<DoctorResource> getDoctors() {
        //List<DoctorResource> hateos = new ArrayList<DoctorResource>();
        //List<Doctor> doctors = service.findAll();
        //for (Doctor doctor : doctors) {
            //DoctorResource res = new DoctorResource
                    //.Builder(doctor.getName())
                    //.demographic(doctor.getDemographic())
                    //.contact(doctor.getContact())
                    //.address(doctor.getAddress())
                    //.specialization(doctor.getSpecialization())
                    //.appointmentList(doctor.getAppointmentList())
                    //.login(doctor.getLogin())
                    //.resid(doctor.getId())
                    //.build();
            //Link doctorsLink = new
                    //Link("http://localhost:8080/doctor/"+res.getResId().toString())
                    //.withRel("doctors");
            //res.add(doctorsLink);
            //hateos.add(res);
        //}
        //return hateos;
    //}

    @RequestMapping(value="/appointment/{id}", method= RequestMethod.GET)
    public List<Appointment> getAppointments(@PathVariable Long id) {
        return service.getAppointments(id);
    }

    @RequestMapping(value = "/doctors/", method = RequestMethod.GET)
    public ResponseEntity<List<Doctor>> listAllDoctors() {
        List<Doctor> doctorList = service.findAll();
        if(doctorList.isEmpty()){
            return new ResponseEntity<List<Doctor>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Doctor>>(doctorList, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> getDoctor(@PathVariable("id") long id) {
        System.out.println("Fetching Doctor with id " + id);
        Doctor doctor = service.findById(id);
        if (doctor == null) {
            System.out.println("Doctor with id " + id + " not found");
            return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createDoctor(@RequestBody Doctor doctor,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Appointment " + doctor.getId());

//     USE THIS IF YOU WANT TO CHECK UNIQUE OBJECT
//      if (SubjectService.isSubjectExist(Subject)) {
//            System.out.println("A Subject with name " + Subject.getName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }

        service.create(doctor);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/doctor/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") long id, @RequestBody Doctor doctor) {
        System.out.println("Updating Doctor " + id);

        Doctor currentDoctor = service.findById(id);

        if (currentDoctor==null) {
            System.out.println("Doctor with id " + id + " not found");
            return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
        }

        Doctor updatedDoctor = new Doctor
                .Builder(currentDoctor.getName())
                .copy(currentDoctor)
                .id(doctor.getId())
                .name(doctor.getName())
                .demographic(doctor.getDemographic())
                .contact(doctor.getContact())
                .address(doctor.getAddress())
                .specialization(doctor.getSpecialization())
                .appointmentList(doctor.getAppointmentList())
                .login(doctor.getLogin())
                .build();
        service.edit(updatedDoctor);
        return new ResponseEntity<Doctor>(updatedDoctor, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Doctor with id " + id);

        Doctor doctor = service.findById(id);
        if (doctor == null) {
            System.out.println("Unable to delete. Doctor with id " + id + " not found");
            return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
        }

        service.delete(doctor);
        return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
    }

}
