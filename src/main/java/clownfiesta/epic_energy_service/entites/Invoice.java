package clownfiesta.epic_energy_service.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate invoice_date;
    private double import_invoice;
    private long number_invoice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "invoicestate_id")
    private InvoiceState invoiceState;

    public Invoice(LocalDate invoice_date, double import_invoice, long number_invoice, Customer customer, InvoiceState invoiceState) {
        this.invoice_date = invoice_date;
        this.import_invoice = import_invoice;
        this.number_invoice = number_invoice;
        this.customer = customer;
        this.invoiceState = invoiceState;
    }


}
