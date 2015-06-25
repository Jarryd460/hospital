package za.ac.cput.hospital.services;

import za.ac.cput.hospital.domain.Doctor;
import za.ac.cput.hospital.domain.Hospital;
import za.ac.cput.hospital.domain.Patient;
import za.ac.cput.hospital.domain.Ward;

import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
public interface HospitalService {

    List<Hospital> getHospitals();

    List<Ward> getWards(Long id);

    List<Doctor> getDoctors(Long id);

    List<Patient> getPatients(Long id);

}
