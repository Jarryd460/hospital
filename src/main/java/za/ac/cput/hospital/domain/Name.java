package za.ac.cput.hospital.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by student on 2015/06/22.
 */
@Embeddable
public class Name implements Serializable {

    private String firstName;
    private String lastName;

    private Name() {}

    public Name(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static class Builder {

        private String firstName;
        private String lastName;

        public Builder(String lastName) {
            this.lastName = lastName;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder copy(Name name) {
            this.firstName = name.firstName;
            this.lastName = name.lastName;
            return this;
        }

        public Name build() {
            return new Name(this);
        }

    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
