package za.ac.cput.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by student on 2015/06/22.
 */
@Entity
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private Name name;
    @Embedded
    private Demographic demographic;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;
    private String specialization;
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name="doctor_id")
    //private List<Appointment> appointmentList;
    @OneToMany(mappedBy = "doctor", targetEntity = Appointment.class, fetch = FetchType.EAGER)
    private List<Appointment> appointmentList;

    private Doctor() {}

    public Doctor(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.demographic = builder.demographic;
        this.contact = builder.contact;
        this.address = builder.address;
        this.specialization = builder.specialization;
        this.appointmentList = builder.appointmentList;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Demographic getDemographic() {
        return demographic;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public static class Builder {

        private Long id;
        private Name name;
        private Demographic demographic;
        private Contact contact;
        private Address address;
        private String specialization;
        //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        //@JoinColumn(name="doctor_id")
        //private List<Appointment> appointmentList;
        private List<Appointment> appointmentList;

        public Builder(Name name) {
            this.name = name;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder demographic(Demographic demographic) {
            this.demographic = demographic;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder contact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder specialization(String specialization) {
            this.specialization = specialization;
            return this;
        }

        public Builder appointmentList(List<Appointment> appointmentList) {
            this.appointmentList = appointmentList;
            return this;
        }

        public Builder copy(Doctor doctor) {
            this.id = doctor.id;
            this.name = doctor.name;
            this.demographic = doctor.demographic;
            this.address = doctor.address;
            this.contact = doctor.contact;
            this.specialization = doctor.specialization;
            this.appointmentList = doctor.appointmentList;
            return this;
        }

        public Doctor build() {
            return new Doctor(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;

        Doctor doctor = (Doctor) o;

        return !(id != null ? !id.equals(doctor.id) : doctor.id != null);
    }


    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name=" + name +
                ", demographic=" + demographic +
                ", contact=" + contact +
                ", address=" + address +
                ", specialization='" + specialization + '\'' +
                ", appointmentList=" + appointmentList +
                '}';
    }

}
