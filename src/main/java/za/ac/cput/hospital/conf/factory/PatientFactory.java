package za.ac.cput.hospital.conf.factory;

import za.ac.cput.hospital.domain.*;

import java.util.List;

/**
 * Created by student on 2015/06/23.
 */
public class PatientFactory {

    public static Patient createPatient(Name name, Demographic demographic, Contact contact, Address address, List<Appointment> appointmentList) {
        return new Patient.Builder(name).demographic(demographic).contact(contact).address(address).appointmentList(appointmentList).build();
    }

}
