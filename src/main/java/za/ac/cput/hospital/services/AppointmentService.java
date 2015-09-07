package za.ac.cput.hospital.services;

import za.ac.cput.hospital.domain.Appointment;
import za.ac.cput.hospital.domain.Invoice;

import java.util.List;

/**
 * Created by student on 2015/06/25.
 */
public interface AppointmentService extends Services<Appointment, Long>{

    List<Invoice> getInvoices(Long id);

}
