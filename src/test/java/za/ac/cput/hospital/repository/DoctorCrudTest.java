package za.ac.cput.hospital.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.*;
import za.ac.cput.hospital.domain.*;
import za.ac.cput.hospital.services.impl.DoctorServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        repository.deleteAll();
        Doctor doctor = DoctorFactory.createDoctor(NameFactory.createName("Jarryd", "Deane"), DemographicFactory.createDemographic(null, null, "04-29-1992"), ContactFactory.createContact("0213937854", "0823451234", "0760984567"), AddressFactory.createAddress("21 Street", "Western Cape", "Cape Town", "South Africa", "7798"), "Surgeon", null, null);
        repository.save(doctor);
        id = doctor.getId();
        Assert.assertNotNull(doctor.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        Doctor doctor = repository.findOne(id);
        Assert.assertEquals(doctor.getName().getFirstName(), "Jarryd");
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
