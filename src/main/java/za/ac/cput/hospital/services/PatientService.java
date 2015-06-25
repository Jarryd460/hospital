package za.ac.cput.hospital.services;

import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Invoice;
import za.ac.cput.hospital.domain.Patient;

import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
public interface PatientService {

    List<Patient> getPatients();

    List<Appointment> getAppointments(Long id);

}
