package za.ac.cput.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.PatientFactory;
import za.ac.cput.hospital.conf.factory.WardFactory;
import za.ac.cput.hospital.domain.*;
import za.ac.cput.hospital.repository.WardRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/06/26.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class WardServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WardService service;
    private Long id;
    @Autowired
    private WardRepository repository;

    @Test
    public void create() throws Exception {
        repository.deleteAll();
        Date date = new Date();
        Patient patient = PatientFactory.createPatient(new Name.Builder("Deane").build(), new Demographic.Builder(date).gender(Sex.Male).build(), new Contact.Builder("0821234567").build(), null, null);
        List<Patient> patientList = new ArrayList<Patient>();
        patientList.add(patient);
        Ward ward = WardFactory.createWard(20, patientList);
        repository.save(ward);
        id=ward.getId();
        Assert.assertNotNull(ward.getId());
    }

    @Test
    public void testGetWards() throws Exception {
        List<Ward> wardList = service.getWards();
        Assert.assertEquals(wardList.size(), 1);
    }

    @Test
    public void testGetPatients() throws Exception {
        List<Patient> patientList = service.getPatients(id);
        Assert.assertEquals(patientList.size(), 1);
    }

}
