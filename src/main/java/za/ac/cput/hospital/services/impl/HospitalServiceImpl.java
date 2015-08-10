package za.ac.cput.hospital.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.hospital.domain.Doctor;
import za.ac.cput.hospital.domain.Hospital;
import za.ac.cput.hospital.domain.Patient;
import za.ac.cput.hospital.domain.Ward;
import za.ac.cput.hospital.repository.HospitalRepository;
import za.ac.cput.hospital.services.HospitalService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
@Service
public class HospitalServiceImpl implements HospitalService{

    @Autowired
    HospitalRepository repository;

    @Override
    public List<Hospital> getHospitals() {
        List<Hospital> allHospitals = new ArrayList<Hospital>();

        Iterable<Hospital> hospitals = repository.findAll();
        for (Hospital hospital : hospitals) {
            allHospitals.add(hospital);
        }
        return allHospitals;
    }

    @Override
    public List<Ward> getWards(Long id) {
        //return repository.findOne(id).getWardList();
        List<Ward> wardList = new ArrayList<Ward>();
        List<Ward> wards = repository.findOne(id).getWardList();

        for (Ward ward : wards) {
            if(!wardList.contains(ward)) {
                wardList.add(ward);
            }
        }

        return wardList;
    }

    @Override
    public List<Doctor> getDoctors(Long id) {
        //return repository.findOne(id).getDoctorList();
        List<Doctor> doctorList = new ArrayList<Doctor>();
        List<Doctor> doctors = repository.findOne(id).getDoctorList();

        for (Doctor doctor : doctors) {
            if(!doctorList.contains(doctor)) {
                doctorList.add(doctor);
            }
        }

        return doctorList;
    }

}
