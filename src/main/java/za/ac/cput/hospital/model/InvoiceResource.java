package za.ac.cput.hospital.model;

import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by student on 2015/08/02.
 */
public class InvoiceResource extends ResourceSupport {

    private Long resid;
    private String date;
    private BigDecimal amount;

    private InvoiceResource() {}

    public InvoiceResource(Builder builder) {
        this.resid = builder.resid;
        this.date = builder.date;
        this.amount = builder.amount;
    }

    public Long getResId() {
        return resid;
    }

    public String getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static class Builder {

        private Long resid;
        private String date;
        private BigDecimal amount;

        public Builder(BigDecimal amount) {
            this.amount = amount;
        }

        public Builder resid(Long resid) {
            this.resid = resid;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder copy(InvoiceResource invoice) {
            this.resid = invoice.resid;
            this.date = invoice.date;
            this.amount = invoice.amount;
            return this;
        }

        public InvoiceResource build() {
            return new InvoiceResource(this);
        }

    }

}
