package za.ac.cput.hospital.conf.factory;

import za.ac.cput.hospital.domain.Patient;
import za.ac.cput.hospital.domain.Ward;

import java.util.List;

/**
 * Created by student on 2015/06/23.
 */
public class WardFactory {

    public static Ward createWard(int capacity, List<Patient>patientList) {
        return new Ward.Builder(capacity).patientList(patientList).build();
    }

}
