package za.ac.cput.hospital.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.hospital.conf.factory.HospitalFactory;

/**
 * Created by student on 2015/06/25.
 */
public class HospitalTest {

    @Test
    public void testCreate() {
        Hospital hospital = HospitalFactory.createHospital("Hospital", "0219753575", new Address.Builder("21 Street").build(), null, null);
        Assert.assertEquals(hospital.getName(), "Hospital");
        Assert.assertEquals(hospital.getContactNumber(), "0219753575");
        Assert.assertEquals(hospital.getAddress().getAddress(), "21 Street");
    }

    @Test
    public void testUpdate() {
        Hospital hospital = HospitalFactory.createHospital("Hospital", "0219753575", new Address.Builder("21 Street").build(), null, null);
        Hospital hospitalCopy = new Hospital.Builder(hospital.getName()).copy(hospital).contactNumber("0219871234").build();
        Assert.assertEquals(hospitalCopy.getName(), "Hospital");
        Assert.assertEquals(hospitalCopy.getContactNumber(), "0219871234");
        Assert.assertEquals(hospitalCopy.getAddress().getAddress(), "21 Street");
    }

}
