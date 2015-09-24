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
import za.ac.cput.hospital.domain.Patient;
import za.ac.cput.hospital.model.PatientResource;
import za.ac.cput.hospital.services.PatientService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
@RestController
@RequestMapping(value="/pat/**")
public class PatientPage {

    @Autowired
    private PatientService service;

    //@RequestMapping(value="/patients", method= RequestMethod.GET)
    //public List<PatientResource> getPatients() {
        //List<PatientResource> hateos = new ArrayList<PatientResource>();
        //List<Patient> patients = service.findAll();
        //for (Patient patient : patients) {
            //PatientResource res = new PatientResource
                    //.Builder(patient.getName())
                    //.demographic(patient.getDemographic())
                    //.contact(patient.getContact())
                    //.address(patient.getAddress())
                    //.appointmentList(patient.getAppointmentList())
                    //.resid(patient.getId())
                    //.build();
            //Link patientsLink = new
                    //Link("http://localhost:8080/patient/"+res.getResId().toString())
                    //.withRel("patients");
            //res.add(patientsLink);
            //hateos.add(res);
        //}
        //return hateos;
    //}

    @RequestMapping(value="/appointment/{id}", method= RequestMethod.GET)
    public List<Appointment> getAppointments(@PathVariable Long id) {
        return service.getAppointments(id);
    }

    @RequestMapping(value = "/patients/", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> listAllPatients() {
        List<Patient> patientList = service.findAll();
        if(patientList.isEmpty()){
            return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Patient>>(patientList, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> getPatient(@PathVariable("id") long id) {
        System.out.println("Fetching Patient with id " + id);
        Patient patient = service.findById(id);
        if (patient == null) {
            System.out.println("Patient with id " + id + " not found");
            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createPatient(@RequestBody Patient patient,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Patient " + patient.getId());

//     USE THIS IF YOU WANT TO CHECK UNIQUE OBJECT
//      if (SubjectService.isSubjectExist(Subject)) {
//            System.out.println("A Subject with name " + Subject.getName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }

        service.create(patient);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/patient/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") long id, @RequestBody Patient patient) {
        System.out.println("Updating Patient " + id);

        Patient currentPatient = service.findById(id);

        if (currentPatient==null) {
            System.out.println("Patient with id " + id + " not found");
            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
        }

        Patient updatedPatient = new Patient
                .Builder(currentPatient.getName())
                .copy(currentPatient)
                .id(patient.getId())
                .name(patient.getName())
                .demographic(patient.getDemographic())
                .contact(patient.getContact())
                .address(patient.getAddress())
                .build();
        service.edit(updatedPatient);
        return new ResponseEntity<Patient>(updatedPatient, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Patient> deletePatient(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Patient with id " + id);

        Patient patient = service.findById(id);
        if (patient == null) {
            System.out.println("Unable to delete. Patient with id " + id + " not found");
            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
        }

        service.delete(patient);
        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }

}
