package za.ac.cput.hospital.model;

import org.springframework.hateoas.ResourceSupport;
import za.ac.cput.hospital.domain.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
public class DoctorResource extends ResourceSupport{

    private Long resid;
    private Name name;
    private Demographic demographic;
    private Contact contact;
    private Address address;
    private String specialization;
    private List<Appointment> appointmentList;
    private Login login;


    private DoctorResource() {}

    public DoctorResource(Builder builder) {
        this.resid = builder.resid;
        this.name = builder.name;
        this.demographic = builder.demographic;
        this.contact = builder.contact;
        this.address = builder.address;
        this.specialization = builder.specialization;
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

    public Address getAddress() {
        return address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public Login getLogin() {
        return login;
    }

    public static class Builder {

        private Long resid;
        private Name name;
        private Demographic demographic;
        private Contact contact;
        private Address address;
        private String specialization;
        private List<Appointment> appointmentList;
        private Login login;

        public Builder(Name name) {
            this.name = name;
        }

        public Builder resid(Long resid) {
            this.resid = resid;
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

        public Builder login(Login login) {
            this.login = login;
            return this;
        }

        public Builder copy(DoctorResource doctor) {
            this.resid = doctor.resid;
            this.name = doctor.name;
            this.demographic = doctor.demographic;
            this.address = doctor.address;
            this.contact = doctor.contact;
            this.specialization = doctor.specialization;
            this.appointmentList = doctor.appointmentList;
            this.login = doctor.login;
            return this;
        }

        public DoctorResource build() {
            return new DoctorResource(this);
        }

    }

}
