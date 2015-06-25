package za.ac.cput.hospital.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.hospital.conf.factory.NameFactory;

/**
 * Created by student on 2015/06/25.
 */
public class NameTest {

    @Test
    public void testCreate() {
        Name name = NameFactory.createName("Jarryd", "Deane");
        Assert.assertEquals(name.getFirstName(), "Jarryd");
        Assert.assertEquals(name.getLastName(), "Deane");
    }

    @Test
    public void testUpdate() {
        Name name = NameFactory.createName("Jarryd", "Deane");
        Name nameCopy = new Name.Builder(name.getLastName()).copy(name).lastName("Arnold").build();
        Assert.assertEquals(nameCopy.getFirstName(), "Jarryd");
        Assert.assertEquals(nameCopy.getLastName(), "Arnold");
    }

}
