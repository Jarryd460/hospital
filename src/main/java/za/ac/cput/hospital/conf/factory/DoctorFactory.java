package za.ac.cput.hospital.conf.factory;

import za.ac.cput.hospital.domain.*;

import java.util.List;

/**
 * Created by student on 2015/06/23.
 */
public class DoctorFactory {

    public static Doctor createDoctor(Name name, Demographic demographic, Contact contact, Address address, String specialization, List<Appointment> appointmentList) {
        return new Doctor.Builder(name).demographic(demographic).contact(contact).address(address).specialization(specialization).appointmentList(appointmentList).build();
    }

}
