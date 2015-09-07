package za.ac.cput.hospital.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Invoice;
import za.ac.cput.hospital.repository.AppointmentRepository;
import za.ac.cput.hospital.services.AppointmentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository repository;

    @Override
    public Appointment findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> allAppointments = new ArrayList<Appointment>();

        Iterable<Appointment> appointments = repository.findAll();
        for (Appointment appointment : appointments) {
            allAppointments.add(appointment);
        }
        return allAppointments;
    }

    @Override
    public List<Invoice> getInvoices(Long id) {
        return repository.findOne(id).getInvoiceList();
    }

    @Override
    public Appointment create(Appointment appointment) {
        return repository.save(appointment);
    }

    @Override
    public Appointment edit(Appointment appointment) {
        return repository.save(appointment);
    }

    @Override
    public void delete(Appointment appointment) {
        repository.delete(appointment);
    }

}
