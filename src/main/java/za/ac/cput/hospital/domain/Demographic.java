package za.ac.cput.hospital.domain;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by student on 2015/06/22.
 */
@Embeddable
public class Demographic implements Serializable {

    private Sex gender;
    private Race race;
    @Temporal(TemporalType.DATE)
    @Past
    private Date dateOfBirth;

}
