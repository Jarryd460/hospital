package za.ac.cput.hospital.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.hospital.conf.factory.WardFactory;

/**
 * Created by student on 2015/06/25.
 */
public class WardTest {

    @Test
    public void testCreate() {
        Ward ward = WardFactory.createWard(20, null);
        Assert.assertEquals(ward.getCapacity(), 20);
        Assert.assertEquals(ward.getPatientList(), null);
    }

    @Test
    public void testUpdate() {
        Ward ward = WardFactory.createWard(20, null);
        Ward wardCopy = new Ward.Builder(ward.getCapacity()).copy(ward).capacity(40).build();
        Assert.assertEquals(wardCopy.getCapacity(), 40);
        Assert.assertEquals(wardCopy.getPatientList(), null);
    }

}
