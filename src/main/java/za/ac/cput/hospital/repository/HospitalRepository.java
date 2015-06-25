package za.ac.cput.hospital.repository;

import org.springframework.data.repository.CrudRepository;
import za.ac.cput.hospital.domain.Hospital;

/**
 * Created by student on 2015/06/25.
 */
public interface HospitalRepository extends CrudRepository<Hospital,Long> {
}
