package za.ac.cput.hospital.services;

import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Doctor;

import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
public interface DoctorService extends Services<Doctor, Long>{

    List<Appointment> getAppointments(Long id);

}
