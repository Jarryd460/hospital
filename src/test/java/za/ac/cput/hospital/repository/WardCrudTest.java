package za.ac.cput.hospital.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.WardFactory;
import za.ac.cput.hospital.domain.Ward;

/**
 * Created by student on 2015/06/25.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class WardCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    WardRepository repository;

    @Test
    public void testCreate() throws Exception {
        Ward ward = WardFactory.createWard(20, null);
        repository.save(ward);
        id = ward.getId();
        Assert.assertNotNull(ward.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        Ward ward = repository.findOne(id);
        Assert.assertEquals(ward.getCapacity(), 20);
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        Ward ward = repository.findOne(id);
        Ward newWard = new Ward.Builder(ward.getCapacity()).copy(ward).capacity(40).build();
        repository.save(newWard);
        Ward updatedWard = repository.findOne(id);
        Assert.assertEquals(updatedWard.getCapacity(), 40);
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Ward ward = repository.findOne(id);
        repository.delete(ward);
        Ward newWard = repository.findOne(id);
        Assert.assertNull(newWard);
    }
}
