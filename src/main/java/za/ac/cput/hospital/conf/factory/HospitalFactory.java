package za.ac.cput.hospital.conf.factory;

import za.ac.cput.hospital.domain.Address;
import za.ac.cput.hospital.domain.Doctor;
import za.ac.cput.hospital.domain.Hospital;
import za.ac.cput.hospital.domain.Ward;

import java.util.List;

/**
 * Created by student on 2015/06/23.
 */
public class HospitalFactory {

    public static Hospital createHospital(String name, String contactNumber, Address address, List<Ward>wardList, List<Doctor> doctorList) {
        return new Hospital.Builder(name).contactNumber(contactNumber).address(address).wardList(wardList).doctorList(doctorList).build();
    }

}
