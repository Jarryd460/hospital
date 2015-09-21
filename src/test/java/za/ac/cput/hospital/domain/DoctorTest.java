package za.ac.cput.hospital.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.hospital.conf.factory.DoctorFactory;

import java.util.Date;

/**
 * Created by student on 2015/06/25.
 */
public class DoctorTest {

    @Test
    public void testCreate() {
        Doctor doctor = DoctorFactory.createDoctor(new Name.Builder("Deane").build(), new Demographic.Builder("04-29-1992").build(), null, null, "Surgeon", null, null);
        Assert.assertEquals(doctor.getName().getLastName(), "Deane");
        Assert.assertEquals(doctor.getDemographic().getDateOfBirth(), "04-29-1992");
        Assert.assertEquals(doctor.getSpecialization(), "Surgeon");
    }

    @Test
    public void testUpdate() {
        Doctor doctor = DoctorFactory.createDoctor(new Name.Builder("Deane").build(), new Demographic.Builder("04-29-1992").build(), null, null, "Surgeon", null, null);
        Doctor doctorCopy = new Doctor.Builder(doctor.getName()).copy(doctor).specialization("Radiologist").build();
        Assert.assertEquals(doctorCopy.getName().getLastName(), "Deane");
        Assert.assertEquals(doctorCopy.getDemographic().getDateOfBirth(), "04-29-1992");
        Assert.assertEquals(doctorCopy.getSpecialization(), "Radiologist");
    }

}
