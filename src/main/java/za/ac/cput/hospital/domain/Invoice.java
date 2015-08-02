package za.ac.cput.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by student on 2015/06/22.
 */
@Entity
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name="appointment_id")
    //private Appointment appointment;
    @NotNull
    private BigDecimal amount;

    private Invoice() {}

    public Invoice(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        //this.appointment = builder.appointment;
        this.amount = builder.amount;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    //public Appointment getAppointment() {
        //return appointment;
    //}

    public BigDecimal getAmount() {
        return amount;
    }

    public static class Builder {

        private Long id;
        private Date date;
        //private Appointment appointment;
        private BigDecimal amount;

        public Builder(BigDecimal amount) {
            this.amount = amount;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        //public Builder appointment(Appointment appointment) {
            //this.appointment = appointment;
            //return this;
        //}

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder copy(Invoice invoice) {
            this.id = invoice.id;
            this.date = invoice.date;
            //this.appointment = invoice.appointment;
            this.amount = invoice.amount;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;

        Invoice invoice = (Invoice) o;

        return !(id != null ? !id.equals(invoice.id) : invoice.id != null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }

}
