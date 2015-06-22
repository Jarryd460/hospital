package za.ac.cput.hospital.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by student on 2015/06/22.
 */
@Embeddable
public class Contact implements Serializable {

    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "{invalid.homePhoneNumber}")
    private String homePhoneNumber;
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "{invalid.mobilePhoneNumber}")
    private String mobilePhoneNumber;
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "{invalid.alternativePhoneNumber}")
    private String alternativePhoneNumber;

}
