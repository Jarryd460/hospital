package za.ac.cput.hospital.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by student on 2015/06/27.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddressTest.class,
        AppointmentTest.class,
        ContactTest.class,
        DemographicTest.class,
        DoctorTest.class,
        InvoiceTest.class,
        LoginTest.class,
        NameTest.class,
        PatientTest.class,
        WardTest.class
})
public class TestSuite {
}
