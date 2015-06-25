package za.ac.cput.hospital.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.DoctorFactory;
import za.ac.cput.hospital.domain.Demographic;
import za.ac.cput.hospital.domain.Doctor;
import za.ac.cput.hospital.domain.Name;

import java.util.Date;

/**
 * Created by student on 2015/06/25.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class DoctorCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    DoctorRepository repository;

    @Test
    public void testCreate() throws Exception {
        Date date = new Date();
        Doctor doctor = DoctorFactory.createDoctor(new Name.Builder("Deane").build(), new Demographic.Builder(date).build(), null, null, "Surgeon", null);
        repository.save(doctor);
        id = doctor.getId();
        Assert.assertNotNull(doctor.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        Doctor doctor = repository.findOne(id);
        Assert.assertEquals(doctor.getName().getLastName(), "Deane");
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        Doctor doctor = repository.findOne(id);
        Doctor newDoctor = new Doctor.Builder(doctor.getName()).copy(doctor).specialization("Radiologist").build();
        repository.save(newDoctor);
        Doctor updatedDoctor = repository.findOne(id);
        Assert.assertEquals(updatedDoctor.getSpecialization(), "Radiologist");
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Doctor doctor = repository.findOne(id);
        repository.delete(doctor);
        Doctor newDoctor = repository.findOne(id);
        Assert.assertNull(newDoctor);
    }

}
