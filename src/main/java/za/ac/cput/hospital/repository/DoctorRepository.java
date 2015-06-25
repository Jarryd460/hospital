package za.ac.cput.hospital.repository;

import org.springframework.data.repository.CrudRepository;
import za.ac.cput.hospital.domain.Doctor;

/**
 * Created by student on 2015/06/25.
 */
public interface DoctorRepository extends CrudRepository<Doctor,Long> {
}
