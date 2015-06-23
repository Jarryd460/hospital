package za.ac.cput.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * Created by student on 2015/06/22.
 */
@Entity
public class Hospital implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "{invalid.contactNumber}")
    private String contactNumber;
    @Embedded
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="hospital_id")
    private List<Ward> wardList;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="hospital_id")
    private List<Doctor> doctorList;

    private Hospital() {}

    public Hospital(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.contactNumber = builder.contactNumber;
        this.address = builder.address;
        this.wardList = builder.wardList;
        this.doctorList = builder.doctorList;
    }

    public Long getId() {
        return id;
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

        private Long id;
        private String name;
        private String contactNumber;
        private Address address;
        private List<Ward> wardList;
        private List<Doctor> doctorList;

        public Builder(String name) {
            this.name = name;
        }

        public Builder id(Long id) {
            this.id = id;
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

        public Builder copy(Hospital hospital) {
            this.id = hospital.id;
            this.name = hospital.name;
            this.contactNumber = hospital.contactNumber;
            this.address = hospital.address;
            this.wardList = hospital.wardList;
            this.doctorList = hospital.doctorList;
            return this;
        }

        public Hospital build() {
            return new Hospital(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospital)) return false;

        Hospital hospital = (Hospital) o;

        return !(id != null ? !id.equals(hospital.id) : hospital.id != null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", address=" + address +
                ", wardList=" + wardList +
                ", doctorList=" + doctorList +
                '}';
    }

}
