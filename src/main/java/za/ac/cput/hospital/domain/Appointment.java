package za.ac.cput.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/06/22.
 */
@Entity
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name="patient_id")
    //private Patient patient;
    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name="doctor_id")
    //private Doctor doctor;
    private String description;
    @NotNull
    private BigDecimal amount;
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name="appointment_id")
    //private List<Invoice> invoiceList;
    //@OneToMany(mappedBy = "appointment", targetEntity = Invoice.class, fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="appointment_id")
    private List<Invoice> invoiceList;

    private Appointment(){}

    public Appointment(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        //this.patient = builder.patient;
        //this.doctor = builder.doctor;
        this.description = builder.description;
        this.amount = builder.amount;
        this.invoiceList = builder.invoiceList;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    //public Patient getPatient() {
        //return patient;
    //}

    //public Doctor getDoctor() {
        //return doctor;
    //}

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public static class Builder {

        private Long id;
        private Date date;
        //private Patient patient;
        //private Doctor doctor;
        private String description;
        private BigDecimal amount;
        //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        //@JoinColumn(name="appointment_id")
        //private List<Invoice> invoiceList;
        private List<Invoice> invoiceList;

        public Builder(Date date) {
            this.date = date;
        }

        //public Builder(Patient patient) {
            //this.patient = patient;
        //}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        //public Builder patient(Patient patient) {
            //this.patient = patient;
            //return this;
        //}

        //public Builder doctor(Doctor doctor) {
            //this.doctor = doctor;
            //return this;
        //}

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder invoiceList(List<Invoice> invoiceList) {
            this.invoiceList = invoiceList;
            return this;
        }

        public Builder copy(Appointment appointment) {
            this.id = appointment.id;
            this.date = appointment.date;
            //this.patient = appointment.patient;
            //this.doctor = appointment.doctor;
            this.description = appointment.description;
            this.amount = appointment.amount;
            this.invoiceList = appointment.invoiceList;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;

        Appointment appointment = (Appointment) o;

        return !(id != null ? !id.equals(appointment.id) : appointment.id != null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", invoiceList=" + invoiceList +
                '}';
    }

}
