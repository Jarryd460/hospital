package za.ac.cput.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.AppointmentFactory;
import za.ac.cput.hospital.conf.factory.PatientFactory;
import za.ac.cput.hospital.domain.*;
import za.ac.cput.hospital.repository.PatientRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/06/26.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class PatientServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PatientService service;
    private Long id;
    @Autowired
    private PatientRepository repository;

    @Test
    public void create() throws Exception {
        repository.deleteAll();
        Date date = new Date();
        Appointment appointment1 = AppointmentFactory.createAppointment(new Date(), "Checkup", new BigDecimal(10000), null);
        Appointment appointment2 = AppointmentFactory.createAppointment(new Date(), "Heart transplant", new BigDecimal(250000), null);
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        Patient patient = PatientFactory.createPatient(new Name.Builder("Deane").build(), new Demographic.Builder(date).gender(Sex.Male).build(), new Contact.Builder("0821234567").build(), null, appointmentList);
        repository.save(patient);
        id=patient.getId();
        Assert.assertNotNull(patient.getId());
    }

    @Test(dependsOnMethods = "create")
    public void testGetPatient() throws Exception {
        Patient patient = service.findById(id);
        Assert.assertNotNull(patient.getId());
    }

    @Test(dependsOnMethods = "testGetPatient")
    public void testGetPatients() throws Exception {
        List<Patient> patientList = service.findAll();
        Assert.assertEquals(patientList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetPatients")
    public void testGetAppointments() throws Exception {
        List<Appointment> appointmentList = service.getAppointments(id);
        Assert.assertEquals(appointmentList.size(), 2);
    }

    @Test(dependsOnMethods = "testGetAppointments")
    public void testEditInvoice() throws Exception {
        Patient patient = repository.findOne(id);
        Patient updatedPatient = new Patient.Builder(patient.getName()).copy(patient).demographic(patient.getDemographic()).contact(patient.getContact()).address(patient.getAddress()).appointmentList(patient.getAppointmentList()).build();
        service.edit(updatedPatient);
        Patient newPatient = repository.findOne(id);
        Assert.assertEquals(newPatient.getName().getLastName(), "Deane");
    }

    @Test(dependsOnMethods = "testEditInvoice")
    public void testDeleteInvoice() throws Exception {
        Patient patient = repository.findOne(id);
        service.delete(patient);
        Patient newPatient = repository.findOne(id);
        Assert.assertNull(newPatient);
    }

}
