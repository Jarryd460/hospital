package za.ac.cput.hospital.model;

import org.springframework.hateoas.ResourceSupport;
import za.ac.cput.hospital.domain.Invoice;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
public class AppointmentResource extends ResourceSupport {

    private Long resid;
    private String date;
    private String description;
    private BigDecimal amount;
    private List<Invoice> invoiceList;

    private AppointmentResource(){}

    public AppointmentResource(Builder builder) {
        this.resid = builder.resid;
        this.date = builder.date;
        this.description = builder.description;
        this.amount = builder.amount;
        this.invoiceList = builder.invoiceList;
    }

    public Long getResId() {
        return resid;
    }

    public String getDate() {
        return date;
    }

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

        private Long resid;
        private String date;
        private String description;
        private BigDecimal amount;
        private List<Invoice> invoiceList;

        public Builder(String date) {
            this.date = date;
        }

        public Builder resid(Long resid) {
            this.resid = resid;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

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

        public Builder copy(AppointmentResource appointment) {
            this.resid = appointment.resid;
            this.date = appointment.date;
            this.description = appointment.description;
            this.amount = appointment.amount;
            this.invoiceList = appointment.invoiceList;
            return this;
        }

        public AppointmentResource build() {
            return new AppointmentResource(this);
        }

    }

}
