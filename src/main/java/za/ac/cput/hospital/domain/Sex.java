package za.ac.cput.hospital.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by student on 2015/06/22.
 */
@Embeddable
public enum Sex implements Serializable {
    Male, Female
}
