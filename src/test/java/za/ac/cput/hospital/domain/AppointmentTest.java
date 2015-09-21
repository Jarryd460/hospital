package za.ac.cput.hospital.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.hospital.conf.factory.AppointmentFactory;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by student on 2015/06/23.
 */
public class AppointmentTest {

    @Test
    public void testCreate() {
        Appointment appointment = AppointmentFactory.createAppointment("04-29-1992", "Checkup", new BigDecimal(1000), null);
        Assert.assertEquals( appointment.getDate(), "04-29-1992");
        Assert.assertEquals(appointment.getDescription(),"Checkup");
        Assert.assertNull(appointment.getInvoiceList());
    }

    @Test
    public void testUpdate() {
        Appointment appointment = AppointmentFactory.createAppointment("04-29-1992", "Checkup", new BigDecimal(1000), null);
        Appointment appointmentCopy = new Appointment.Builder(appointment.getDate()).copy(appointment).date("04-29-1994").build();
        Assert.assertEquals(appointmentCopy.getDate(), "04-29-1994");
        Assert.assertEquals(appointmentCopy.getDescription(), "Checkup");
        Assert.assertNull(appointmentCopy.getInvoiceList());
    }

}
