package za.ac.cput.hospital.model;

import org.springframework.hateoas.ResourceSupport;
import za.ac.cput.hospital.domain.Address;
import za.ac.cput.hospital.domain.Doctor;
import za.ac.cput.hospital.domain.Ward;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
public class HospitalResource extends ResourceSupport{

    private Long resid;
    private String name;
    private String contactNumber;
    private Address address;
    private List<Ward> wardList;
    private List<Doctor> doctorList;

    private HospitalResource() {}

    public HospitalResource(Builder builder) {
        this.resid = builder.resid;
        this.name = builder.name;
        this.contactNumber = builder.contactNumber;
        this.address = builder.address;
        this.wardList = builder.wardList;
        this.doctorList = builder.doctorList;
    }

    public Long getResId() {
        return resid;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Address getAddress() {
        return address;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public List<Ward> getWardList() {
        return wardList;
    }

    public static class Builder {

        private Long resid;
        private String name;
        private String contactNumber;
        private Address address;
        private List<Ward> wardList;
        private List<Doctor> doctorList;

        public Builder(String name) {
            this.name = name;
        }

        public Builder resid(Long resid) {
            this.resid = resid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder contactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder wardList(List<Ward> wardList) {
            this.wardList = wardList;
            return this;
        }

        public Builder doctorList(List<Doctor> doctorList) {
            this.doctorList = doctorList;
            return this;
        }

        public Builder copy(HospitalResource hospital) {
            this.resid = hospital.resid;
            this.name = hospital.name;
            this.contactNumber = hospital.contactNumber;
            this.address = hospital.address;
            this.wardList = hospital.wardList;
            this.doctorList = hospital.doctorList;
            return this;
        }

        public HospitalResource build() {
            return new HospitalResource(this);
        }

    }

}
