package za.ac.cput.hospital.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.AppointmentFactory;
import za.ac.cput.hospital.conf.factory.DoctorFactory;
import za.ac.cput.hospital.conf.factory.PatientFactory;
import za.ac.cput.hospital.domain.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class AppointmentCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;
    //private Long docId;

    @Autowired
    AppointmentRepository repository;
    //@Autowired
    //DoctorRepository doctorRepository;
    //@Autowired
    //PatientRepository patientRepository;

    @Test
    public void testCreate() throws Exception {
        Date date = new Date();
        //Doctor doctor = DoctorFactory.createDoctor(new Name.Builder("Deane").build(), new Demographic.Builder(date).build(), null, null, "Surgeon", null);
        //Patient patient = PatientFactory.createPatient(new Name.Builder("Deane").build(), new Demographic.Builder(date).gender(Sex.Male).build(), new Contact.Builder("0821234567").build(), null);
        Appointment appointment = AppointmentFactory.createAppointment(date, "Checkup", new BigDecimal(10000), null);
        //doctor.getAppointmentList().add(appointment);
        //patient.getAppointmentList().add(appointment);
        //doctorRepository.save(doctor);
        //docId = doctor.getId();
        //patientRepository.save(patient);
        repository.save(appointment);
        id = appointment.getId();
        Assert.assertNotNull(appointment.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        Appointment appointment = repository.findOne(id);
        Assert.assertEquals(appointment.getDescription(), "Checkup");
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        Appointment appointment = repository.findOne(id);
        Appointment newAppointment = new Appointment.Builder(appointment.getDate()).copy(appointment).amount(new BigDecimal(20000)).build();
        repository.save(newAppointment);
        Appointment updatedAppointment = repository.findOne(id);
        DecimalFormat df = new DecimalFormat("#.00");
        Assert.assertEquals(df.format(updatedAppointment.getAmount()), df.format(20000));
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Appointment appointment = repository.findOne(id);
        //Doctor doctor = doctorRepository.findOne(docId);
        //List<Appointment> appointmentList = doctor.getAppointmentList();
        //appointmentList.remove(appointment);
        //Doctor newDoctor = new Doctor.Builder(doctor.getName()).copy(doctor).appointmentList(appointmentList).build();
        //doctorRepository.save(newDoctor);
        //doctor.getAppointmentList().remove(appointment);
        repository.delete(appointment);
        //doctorRepository.save(new Doctor.Builder(doctor.getName()).copy(doctor).appointmentList(appointmentList).build());
        //doctor.getAppointmentList().remove(appointment);
        //Assert.assertEquals(doctor.getAppointmentList().size(), 1);
        //doctorRepository.save(doctor);
        //repository.delete(appointment);
        Appointment newAppointment = repository.findOne(id);
        //Assert.assertEquals(appointment.getDoctor().getName().getLastName(), "Deane");
        Assert.assertNull(newAppointment);
    }

}
