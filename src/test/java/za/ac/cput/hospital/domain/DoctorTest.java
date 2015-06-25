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
        Date date = new Date();
        Doctor doctor = DoctorFactory.createDoctor(new Name.Builder("Deane").build(), new Demographic.Builder(date).build(), null, null, "Surgeon", null);
        Assert.assertEquals(doctor.getName().getLastName(), "Deane");
        Assert.assertEquals(doctor.getDemographic().getDateOfBirth(), date);
        Assert.assertEquals(doctor.getSpecialization(), "Surgeon");
    }

    @Test
    public void testUpdate() {
        Date date = new Date();
        Doctor doctor = DoctorFactory.createDoctor(new Name.Builder("Deane").build(), new Demographic.Builder(date).build(), null, null, "Surgeon", null);

        Doctor doctorCopy = new Doctor.Builder(doctor.getName()).copy(doctor).specialization("Radiologist").build();
        Assert.assertEquals(doctorCopy.getName().getLastName(), "Deane");
        Assert.assertEquals(doctorCopy.getDemographic().getDateOfBirth(), date);
        Assert.assertEquals(doctorCopy.getSpecialization(), "Radiologist");
    }

}
