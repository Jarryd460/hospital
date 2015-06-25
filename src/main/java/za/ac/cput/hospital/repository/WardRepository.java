package za.ac.cput.hospital.repository;

import org.springframework.data.repository.CrudRepository;
import za.ac.cput.hospital.domain.Ward;

/**
 * Created by student on 2015/06/25.
 */
public interface WardRepository extends CrudRepository<Ward,Long> {
}
