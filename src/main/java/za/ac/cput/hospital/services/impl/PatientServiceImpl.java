package za.ac.cput.hospital.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Invoice;
import za.ac.cput.hospital.domain.Patient;
import za.ac.cput.hospital.repository.PatientRepository;
import za.ac.cput.hospital.services.PatientService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    PatientRepository repository;

    @Override
    public Patient getPatient(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Patient> getPatients() {
        List<Patient> allPatients = new ArrayList<Patient>();

        Iterable<Patient> patients = repository.findAll();
        for (Patient patient : patients) {
            allPatients.add(patient);
        }
        return allPatients;
    }

    @Override
    public List<Appointment> getAppointments(Long id) {
        return repository.findOne(id).getAppointmentList();
    }

    @Override
    public void create(Patient patient) {
        repository.save(patient);
    }

    @Override
    public void edit(Patient patient) {
        Patient updatedPatient = new Patient.Builder(patient.getName()).copy(patient).demographic(patient.getDemographic()).contact(patient.getContact()).address(patient.getAddress()).appointmentList(patient.getAppointmentList()).build();
        repository.save(updatedPatient);
    }

    @Override
    public void delete(Patient patient) {
        repository.delete(patient);
    }

}
