package za.ac.cput.hospital.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.hospital.conf.factory.AppointmentFactory;

import java.util.Date;

/**
 * Created by student on 2015/06/23.
 */
public class AppointmentTest {

    @Test
    public void testCreate() {
        Date date = new Date();
        Appointment appointment = AppointmentFactory.createAppointment(date, null, null, "Checkup", null);
        Assert.assertEquals( appointment.getDate(), date);
        Assert.assertEquals(appointment.getDescription(),"Checkup");
        Assert.assertNull(appointment.getInvoiceList());
    }

    @Test
    public void testUpdate() {
        Date date = new Date();
        Appointment appointment = AppointmentFactory.createAppointment(date, null, null, "Checkup", null);
        Appointment appointmentCopy = new Appointment.Builder(appointment.getPatient()).copy(appointment).date(new Date()).build();
        Assert.assertEquals(appointmentCopy.getDate(), date);
        Assert.assertEquals(appointmentCopy.getDescription(), "Checkup");
        Assert.assertNull(appointmentCopy.getInvoiceList());
    }

}
