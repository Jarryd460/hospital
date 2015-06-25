package za.ac.cput.hospital.repository;

import org.springframework.data.repository.CrudRepository;
import za.ac.cput.hospital.domain.Appointment;

/**
 * Created by student on 2015/06/25.
 */
public interface AppointmentRepository extends CrudRepository<Appointment,Long> {
}
