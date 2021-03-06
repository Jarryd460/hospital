package za.ac.cput.hospital.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.hospital.conf.factory.PatientFactory;

import java.util.Date;

/**
 * Created by student on 2015/06/25.
 */
public class PatientTest {

    @Test
    public void testCreate() {
        Patient patient = PatientFactory.createPatient(new Name.Builder("Deane").build(), new Demographic.Builder("04-29-1992").build(), new Contact.Builder("0821234567").build(), null, null);
        Assert.assertEquals(patient.getName().getLastName(), "Deane");
        Assert.assertEquals(patient.getDemographic().getDateOfBirth(), "04-29-1992");
        Assert.assertEquals(patient.getContact().getMobilePhoneNumber(), "0821234567");
    }

    @Test
    public void testUpdate() {
        Patient patient = PatientFactory.createPatient(new Name.Builder("Deane").build(), new Demographic.Builder("04-29-1992").build(), new Contact.Builder("0821234567").build(), null, null);
        Patient patientCopy = new Patient.Builder(patient.getName()).copy(patient).name(new Name.Builder("Doe").build()).contact(new Contact.Builder("1234567890").build()).build();
        Assert.assertEquals(patientCopy.getName().getLastName(), "Doe");
        Assert.assertEquals(patientCopy.getDemographic().getDateOfBirth(), "04-29-1992");
        Assert.assertEquals(patientCopy.getContact().getMobilePhoneNumber(), "1234567890");
    }

}
