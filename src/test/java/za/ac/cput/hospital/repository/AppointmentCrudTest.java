package za.ac.cput.hospital.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.AppointmentFactory;
import za.ac.cput.hospital.domain.Appointment;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by student on 2015/06/25.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class AppointmentCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    AppointmentRepository repository;

    @Test
    public void testCreate() throws Exception {
        Date date = new Date();
        Appointment appointment = AppointmentFactory.createAppointment(date, null, null, "Checkup", new BigDecimal(10000), null);
        repository.save(appointment);
        id = appointment.getId();
        Assert.assertNotNull(appointment.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        Appointment appointment = repository.findOne(id);
        DecimalFormat df = new DecimalFormat("#.00");
        Assert.assertEquals(df.format(appointment.getAmount()), df.format(10000));
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        Appointment appointment = repository.findOne(id);
        Appointment newAppointment = new Appointment.Builder(appointment.getPatient()).copy(appointment).id(appointment.getId()).amount(new BigDecimal(20000)).build();
        repository.save(newAppointment);
        Appointment updatedAppointment = repository.findOne(id);
        DecimalFormat df = new DecimalFormat("#.00");
        Assert.assertEquals(df.format(updatedAppointment.getAmount()), df.format(20000));
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Appointment appointment = repository.findOne(id);
        repository.delete(appointment);
        Appointment newAppointment = repository.findOne(id);
        Assert.assertNull(newAppointment);
    }

}
