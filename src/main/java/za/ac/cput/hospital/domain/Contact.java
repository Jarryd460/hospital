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

    private Contact() {}

    public Contact(Builder builder) {
        this.homePhoneNumber = builder.homePhoneNumber;
        this.mobilePhoneNumber = builder.mobilePhoneNumber;
        this.alternativePhoneNumber = builder.alternativePhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getAlternativePhoneNumber() {
        return alternativePhoneNumber;
    }

    public static class Builder {

        private String homePhoneNumber;
        private String mobilePhoneNumber;
        private String alternativePhoneNumber;

        public Builder(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
        }

        public Builder homePhoneNumber(String homePhoneNumber) {
            this.homePhoneNumber = homePhoneNumber;
            return this;
        }

        public Builder mobilePhoneNumber(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
            return this;
        }

        public Builder alternativePhoneNumber(String alternativePhoneNumber) {
            this.alternativePhoneNumber = alternativePhoneNumber;
            return this;
        }

        public Builder copy(Contact contact) {
            this.homePhoneNumber = contact.homePhoneNumber;
            this.mobilePhoneNumber = contact.mobilePhoneNumber;
            this.alternativePhoneNumber = contact.alternativePhoneNumber;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }

    }

    @Override
    public String toString() {
        return "Contact{" +
                "homePhoneNumber='" + homePhoneNumber + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", alternativePhoneNumber='" + alternativePhoneNumber + '\'' +
                '}';
    }

}
