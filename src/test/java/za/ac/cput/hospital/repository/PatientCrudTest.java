package za.ac.cput.hospital.repository;

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

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by student on 2015/06/25.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class PatientCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    PatientRepository repository;

    @Test
    public void testCreate() throws Exception {
        repository.deleteAll();
        Patient patient = PatientFactory.createPatient(new Name.Builder("Deane").build(), new Demographic.Builder("04-29-1992").gender(Sex.Male).build(), new Contact.Builder("0821234567").build(), null, null);
        repository.save(patient);
        id = patient.getId();
        Assert.assertNotNull(patient.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        Patient patient = repository.findOne(id);
        Assert.assertEquals(patient.getName().getLastName(), "Deane");
        Assert.assertEquals(patient.getDemographic().getGender(), Sex.Male);
        Assert.assertEquals(patient.getContact().getMobilePhoneNumber(), "0821234567");
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        Patient patient = repository.findOne(id);
        Patient newPatient = new Patient.Builder(patient.getName()).copy(patient).name(new Name.Builder("Doe").build()).contact(new Contact.Builder("1234567890").build()).build();
        repository.save(newPatient);
        Patient updatedPatient = repository.findOne(id);
        Assert.assertEquals(updatedPatient.getName().getLastName(), "Doe");
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Patient patient = repository.findOne(id);
        repository.delete(patient);
        Patient newPatient = repository.findOne(id);
        Assert.assertNull(newPatient);
    }
}
