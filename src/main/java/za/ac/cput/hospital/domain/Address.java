package za.ac.cput.hospital.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by student on 2015/06/22.
 */
@Embeddable
public class Address implements Serializable {

    private String address;
    private String province;
    private String city;
    private String country;
    private String zipCode;

}
