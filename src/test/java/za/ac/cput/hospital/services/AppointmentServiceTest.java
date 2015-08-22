package za.ac.cput.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.AppointmentFactory;
import za.ac.cput.hospital.conf.factory.InvoiceFactory;
import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Invoice;
import za.ac.cput.hospital.domain.Patient;
import za.ac.cput.hospital.repository.AppointmentRepository;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/06/26.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class AppointmentServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AppointmentService service;
    private Long id;
    @Autowired
    private AppointmentRepository repository;

    @Test
    public void create() throws Exception {
        repository.deleteAll();
        Invoice invoice1 = InvoiceFactory.createInvoice(new Date(), new BigDecimal(20000));
        Invoice invoice2 = InvoiceFactory.createInvoice(new Date(), new BigDecimal(40000));
        List<Invoice> invoiceList1 = new ArrayList<Invoice>();
        invoiceList1.add(invoice1);
        List<Invoice> invoiceList2 = new ArrayList<Invoice>();
        invoiceList2.add(invoice2);
        Appointment appointment1 = AppointmentFactory.createAppointment(new Date(),"Checkup", new BigDecimal(10000), invoiceList1);
        Appointment appointment2 = AppointmentFactory.createAppointment(new Date(), "Heart transplant", new BigDecimal(250000), invoiceList2);
        repository.save(appointment1);
        repository.save(appointment2);
        id=appointment1.getId();
        Assert.assertNotNull(appointment1.getId());
    }

    @Test(dependsOnMethods = "create")
    public void testGetAppointment() throws Exception {
        Appointment appointment = service.getAppointment(id);
        Assert.assertEquals(appointment.getDescription(), "Checkup");
    }

    @Test(dependsOnMethods = "testGetAppointment")
    public void testGetAppointments() throws Exception {
        List<Appointment> appointmentList = service.getAppointments();
        Assert.assertEquals(appointmentList.size(), 2);
    }

    @Test(dependsOnMethods = "testGetAppointments")
    public void testGetInvoices() throws Exception {
        List<Invoice> invoiceList = service.getInvoices(id);
        Assert.assertEquals(invoiceList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetInvoices")
    public void testCreateAppointment() throws Exception {
        Appointment appointment = AppointmentFactory.createAppointment(new Date(),"Checkup", new BigDecimal(10000), null);
        service.create(appointment);
        Assert.assertNotNull(appointment.getId());
   }

    @Test(dependsOnMethods = "testCreateAppointment")
    public void testEditAppointment() throws Exception {
        Appointment appointment = repository.findOne(id);
        Appointment updatedAppointment = new Appointment.Builder(appointment.getDate()).copy(appointment).amount(new BigDecimal(10)).build();
        service.edit(updatedAppointment);
        Appointment newAppointment = repository.findOne(id);
        DecimalFormat df = new DecimalFormat("#.00");
        Assert.assertEquals(df.format(newAppointment.getAmount()), df.format(10));
    }

    @Test(dependsOnMethods = "testEditAppointment")
    public void testDeleteAppointment() throws Exception {
        Appointment appointment = repository.findOne(id);
        service.delete(appointment);
        Appointment newAppointment = repository.findOne(id);
        Assert.assertNull(newAppointment);
    }

}
