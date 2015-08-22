package za.ac.cput.hospital.services;

import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Invoice;

import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
public interface AppointmentService {

    Appointment getAppointment(Long id);

    List<Appointment> getAppointments();

    List<Invoice> getInvoices(Long id);

    void create(Appointment appointment);

    void edit(Appointment appointment);

    void delete(Appointment appointment);

}
