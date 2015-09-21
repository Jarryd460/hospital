package za.ac.cput.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.AppointmentFactory;
import za.ac.cput.hospital.conf.factory.DoctorFactory;
import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Demographic;
import za.ac.cput.hospital.domain.Doctor;
import za.ac.cput.hospital.domain.Name;
import za.ac.cput.hospital.repository.DoctorRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/06/26.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class DoctorServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DoctorService service;
    private Long id;
    @Autowired
    private DoctorRepository repository;

    @Test
    public void create() throws Exception {
        repository.deleteAll();
        Appointment appointment1 = AppointmentFactory.createAppointment("04-29-1992", "Checkup", new BigDecimal(10000), null);
        Appointment appointment2 = AppointmentFactory.createAppointment("04-29-1992", "Heart transplant", new BigDecimal(250000), null);
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        Doctor doctor = DoctorFactory.createDoctor(new Name.Builder("Deane").build(), new Demographic.Builder("04-29-1992").build(), null, null, "Surgeon", appointmentList, null);
        repository.save(doctor);
        id=doctor.getId();
        Assert.assertNotNull(doctor.getId());
    }

    @Test(dependsOnMethods = "create")
    public void testGetDoctor() throws Exception {
        Doctor doctor = service.findById(id);
        Assert.assertEquals(doctor.getName().getLastName(), "Deane");
    }

    @Test(dependsOnMethods = "testGetDoctor")
    public void testGetDoctors() throws Exception {
        List<Doctor> doctorList = service.findAll();
        Assert.assertEquals(doctorList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetDoctors")
    public void testGetAppointments() throws Exception {
        List<Appointment> appointmentList = service.getAppointments(id);
        Assert.assertEquals(appointmentList.size(), 2);
    }

    @Test(dependsOnMethods = "testGetAppointments")
    public void testEditDoctor() throws Exception {
        Doctor doctor = repository.findOne(id);
        Doctor updatedDoctor = new Doctor.Builder(doctor.getName()).copy(doctor).specialization("Unknown").build();
        service.edit(updatedDoctor);
        Doctor newDoctor = repository.findOne(id);
        Assert.assertEquals(newDoctor.getSpecialization(), "Unknown");
    }

    @Test(dependsOnMethods = "testEditDoctor")
    public void testDeleteDoctor() throws Exception {
        Doctor doctor = repository.findOne(id);
        service.delete(doctor);
        Doctor newDoctor = repository.findOne(id);
        Assert.assertNull(newDoctor);
    }

}
