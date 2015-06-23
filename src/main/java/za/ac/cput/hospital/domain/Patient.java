package za.ac.cput.hospital.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by student on 2015/06/22.
 */
@Entity
public class Patient implements Serializable {

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
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name="patient_id")
    //private List<Appointment> appointmentList;
    @OneToMany(mappedBy = "patient", targetEntity = Appointment.class, fetch = FetchType.EAGER)
    private List<Appointment> appointmentList;

    private Patient() {}

    public Patient(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.demographic = builder.demographic;
        this.contact = builder.contact;
        this.address = builder.address;
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

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public Address getAddress() {
        return address;
    }

    public static class Builder {

        private Long id;
        private Name name;
        private Demographic demographic;
        private Contact contact;
        private Address address;
        //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        //@JoinColumn(name="patient_id")
        //private List<Appointment> appointmentList;
        private List<Appointment> appointmentList;

        public Builder(Name name) {
            this.name = name;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder demographic(Demographic demographic) {
            this.demographic = demographic;
            return this;
        }

        public Builder contact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder appointmentList(List<Appointment> appointmentList) {
            this.appointmentList = appointmentList;
            return this;
        }

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder copy(Patient patient) {
            this.id = patient.id;
            this.name = patient.name;
            this.demographic = patient.demographic;
            this.contact = patient.contact;
            this.address = patient.address;
            this.appointmentList = patient.appointmentList;
            return this;
        }

        public Patient build() {
            return new Patient(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;

        Patient patient = (Patient) o;

        return !(id != null ? !id.equals(patient.id) : patient.id != null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name=" + name +
                ", demographic=" + demographic +
                ", contact=" + contact +
                ", address=" + address +
                ", appointmentList=" + appointmentList +
                '}';
    }

}
