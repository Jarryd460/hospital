package za.ac.cput.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import za.ac.cput.hospital.App;
import za.ac.cput.hospital.conf.factory.*;
import za.ac.cput.hospital.domain.*;
import za.ac.cput.hospital.repository.HospitalRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class HospitalServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HospitalService service;
    private Long id;
    @Autowired
    private HospitalRepository repository;

    @Test
    public void create() throws Exception {
        repository.deleteAll();
        Date date = new Date();
        //Patient patient1 = PatientFactory.createPatient(new Name.Builder("Deane").build(), new Demographic.Builder(date).build(), new Contact.Builder("0821234567").build(), null, null);
        //Patient patient2 = PatientFactory.createPatient(new Name.Builder("Anders").build(), new Demographic.Builder(date).build(), new Contact.Builder("0824321456").build(), null, null);
        List<Patient> patientList = new ArrayList<Patient>();
        //patientList.add(patient1);
        //patientList.add(patient2);
        Ward ward1 = WardFactory.createWard(20, patientList);
        Ward ward2 = WardFactory.createWard(15, null);
        Ward ward3 = WardFactory.createWard(10, null);
        List<Ward> wardList = new ArrayList<Ward>();
        wardList.add(ward1);
        wardList.add(ward2);
        wardList.add(ward3);
        Doctor doctor1 = DoctorFactory.createDoctor(NameFactory.createName("Jarryd", "Deane"), DemographicFactory.createDemographic(null, null, date), ContactFactory.createContact("0213937854", "0823451234", "0760984567"), AddressFactory.createAddress("21 Street", "Western Cape", "Cape Town", "South Africa", "7798"), "Surgeon", null);
        Doctor doctor2 = DoctorFactory.createDoctor(NameFactory.createName("Jarryd", "Deane"), DemographicFactory.createDemographic(null, null, date), ContactFactory.createContact("0213937854", "0823451234", "0760984567"), AddressFactory.createAddress("21 Street", "Western Cape", "Cape Town", "South Africa", "7798"), "Surgeon", null);
        List<Doctor> doctorList = new ArrayList<Doctor>();
        doctorList.add(doctor1);
        doctorList.add(doctor2);
        Hospital hospital1 = HospitalFactory.createHospital("Hospital1", "0219753575", new Address.Builder("21 Street").build(), wardList, doctorList);
        repository.save(hospital1);
        id=hospital1.getId();
        Assert.assertNotNull(hospital1.getId());
    }

    @Test(dependsOnMethods = "create")
    public void testGetHospitals() throws Exception {
        List<Hospital> hospitalList = service.getHospitals();
        Assert.assertEquals(hospitalList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetHospitals")
    public void testGetWards() throws Exception {
        List<Ward> wardList = service.getWards(id);
        Assert.assertEquals(wardList.size(), 3);
    }


    @Test
    public void testGetDoctors() throws Exception {
        List<Doctor> doctorList = service.getDoctors(id);
        Assert.assertEquals(doctorList.size(), 2);
    }

}
