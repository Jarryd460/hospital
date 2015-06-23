package za.ac.cput.hospital.conf.factory;

import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Doctor;
import za.ac.cput.hospital.domain.Invoice;
import za.ac.cput.hospital.domain.Patient;

import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/06/23.
 */
public class AppointmentFactory {

    public static Appointment createAppointment(Date date, Patient patient, Doctor doctor, String description, List<Invoice> invoiceList) {
        return new Appointment.Builder(patient).date(date).doctor(doctor).description(description).invoiceList(invoiceList).build();
    }

}
