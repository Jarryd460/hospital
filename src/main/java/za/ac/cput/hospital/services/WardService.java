package za.ac.cput.hospital.services;

import za.ac.cput.hospital.domain.Patient;
import za.ac.cput.hospital.domain.Ward;

import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
public interface WardService {

    List<Ward> getWards();

    List<Patient> getPatients(Long id);

}
