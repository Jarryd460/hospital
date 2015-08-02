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
        Date date = new Date();
        Appointment appointment1 = AppointmentFactory.createAppointment(new Date(), "Checkup", new BigDecimal(10000), null);
        Appointment appointment2 = AppointmentFactory.createAppointment(new Date(), "Heart transplant", new BigDecimal(250000), null);
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        Doctor doctor = DoctorFactory.createDoctor(new Name.Builder("Deane").build(), new Demographic.Builder(date).build(), null, null, "Surgeon", appointmentList);
        repository.save(doctor);
        id=doctor.getId();
        Assert.assertNotNull(doctor.getId());
    }

    @Test
    public void testGetDoctors() throws Exception {
        List<Doctor> doctorList = service.getDoctors();
        Assert.assertEquals(doctorList.size(), 1);
    }

    @Test
    public void testGetAppointments() throws Exception {
        List<Appointment> appointmentList = service.getAppointments(id);
        Assert.assertEquals(appointmentList.size(), 2);
    }

}
