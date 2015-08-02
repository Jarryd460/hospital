package za.ac.cput.hospital.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
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

    private Demographic() {}

    public Demographic(Builder builder) {
        this.gender = builder.gender;
        this.race = builder.race;
        this.dateOfBirth = builder.dateOfBirth;
    }

    public Sex getGender() {
        return gender;
    }

    public Race getRace() {
        return race;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public static class Builder {

        private Sex gender;
        private Race race;
        private Date dateOfBirth;

        public Builder(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Builder gender(Sex gender) {
            this.gender = gender;
            return this;
        }

        public Builder race(Race race) {
            this.race = race;
            return this;
        }

        public Builder dateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder copy(Demographic demographic) {
            this.gender = demographic.gender;
            this.race = demographic.race;
            this.dateOfBirth = demographic.dateOfBirth;
            return this;
        }

        public Demographic build() {
            return new Demographic(this);
        }

    }

    @Override
    public String toString() {
        return "Demographic{" +
                "gender=" + gender +
                ", race=" + race +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}
