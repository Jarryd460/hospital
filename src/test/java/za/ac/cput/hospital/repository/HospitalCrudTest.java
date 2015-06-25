package za.ac.cput.hospital.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.HospitalFactory;
import za.ac.cput.hospital.domain.Address;
import za.ac.cput.hospital.domain.Hospital;

/**
 * Created by student on 2015/06/25.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class HospitalCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    HospitalRepository repository;

    @Test
    public void testCreate() throws Exception {
        Hospital hospital = HospitalFactory.createHospital("Hospital", "0219753575", new Address.Builder("21 Street").build(), null, null);
        repository.save(hospital);
        id = hospital.getId();
        Assert.assertNotNull(hospital.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        Hospital hospital = repository.findOne(id);
        Assert.assertEquals(hospital.getName(), "Hospital");
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        Hospital hospital = repository.findOne(id);
        Hospital newHospital = new Hospital.Builder(hospital.getName()).copy(hospital).contactNumber("0219871234").build();
        repository.save(newHospital);
        Hospital updatedHospital = repository.findOne(id);
        Assert.assertEquals(updatedHospital.getContactNumber(), "0219871234");
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Hospital hospital = repository.findOne(id);
        repository.delete(hospital);
        Hospital newHospital = repository.findOne(id);
        Assert.assertNull(newHospital);
    }


}
