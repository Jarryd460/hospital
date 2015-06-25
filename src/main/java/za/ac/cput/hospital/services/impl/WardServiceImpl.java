package za.ac.cput.hospital.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.hospital.domain.Patient;
import za.ac.cput.hospital.domain.Ward;
import za.ac.cput.hospital.repository.WardRepository;
import za.ac.cput.hospital.services.WardService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
@Service
public class WardServiceImpl implements WardService{

    @Autowired
    WardRepository repository;

    @Override
    public List<Ward> getWards() {
        List<Ward> allWards = new ArrayList<Ward>();

        Iterable<Ward> wards = repository.findAll();
        for (Ward ward : wards) {
            allWards.add(ward);
        }
        return allWards;
    }

    @Override
    public List<Patient> getPatients(Long id) {
        return repository.findOne(id).getPatientList();
    }

}
