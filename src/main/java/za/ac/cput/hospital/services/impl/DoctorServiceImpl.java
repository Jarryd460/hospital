package za.ac.cput.hospital.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Doctor;
import za.ac.cput.hospital.repository.DoctorRepository;
import za.ac.cput.hospital.services.DoctorService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    DoctorRepository repository;

    @Override
    public List<Doctor> getDoctors() {
        List<Doctor> allDoctors = new ArrayList<Doctor>();

        Iterable<Doctor> doctors = repository.findAll();
        for (Doctor doctor : doctors) {
            allDoctors.add(doctor);
        }
        return allDoctors;
    }

    @Override
    public List<Appointment> getAppointments(Long id) {
        return repository.findOne(id).getAppointmentList();
    }

}
