package za.ac.cput.hospital.model;

import org.springframework.hateoas.ResourceSupport;
import za.ac.cput.hospital.domain.Patient;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
public class WardResource extends ResourceSupport {

    private Long resid;
    private int capacity;
    private List<Patient> patientList;

    private WardResource() {}

    public WardResource(Builder builder) {
        this.resid = builder.resid;
        this.capacity = builder.capacity;
        this.patientList = builder.patientList;
    }

    public Long getResId() {
        return resid;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public static class Builder {

        private Long resid;
        private int capacity;
        private List<Patient> patientList;

        public Builder(int capacity) {
            this.capacity = capacity;
        }

        public Builder resid(Long resid) {
            this.resid = resid;
            return this;
        }

        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder patientList(List<Patient> patientList) {
            this.patientList = patientList;
            return this;
        }

        public Builder copy(WardResource ward) {
            this.resid = ward.resid;
            this.capacity = ward.capacity;
            this.patientList = ward.patientList;
            return this;
        }

        public WardResource build() {
            return new WardResource(this);
        }

    }

}
