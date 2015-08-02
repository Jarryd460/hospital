package za.ac.cput.hospital.model;

import org.springframework.hateoas.ResourceSupport;
import za.ac.cput.hospital.domain.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
public class PatientResource extends ResourceSupport {

    private Long resid;
    private Name name;
    private Demographic demographic;
    private Contact contact;
    private Address address;
    private List<Appointment> appointmentList;

    private PatientResource() {}

    public PatientResource(Builder builder) {
        this.resid = builder.resid;
        this.name = builder.name;
        this.demographic = builder.demographic;
        this.contact = builder.contact;
        this.address = builder.address;
        this.appointmentList = builder.appointmentList;
    }

    public Long getResId() {
        return resid;
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

        private Long resid;
        private Name name;
        private Demographic demographic;
        private Contact contact;
        private Address address;
        private List<Appointment> appointmentList;

        public Builder(Name name) {
            this.name = name;
        }

        public Builder resid(Long resid) {
            this.resid = resid;
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

        public Builder copy(PatientResource patient) {
            this.resid = patient.resid;
            this.name = patient.name;
            this.demographic = patient.demographic;
            this.contact = patient.contact;
            this.address = patient.address;
            this.appointmentList = patient.appointmentList;
            return this;
        }

        public PatientResource build() {
            return new PatientResource(this);
        }

    }

}
