package clownfiesta.epic_energy_service.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "fatture")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fattura", nullable = false)
    private Long id;

    @Column(name = "data_fattura", nullable = false)
    private LocalDate invoiceDate;

    @Column(name = "importo_fattura", nullable = false)
    private double importInvoice;

    @Column(name = "numero_fattura", nullable = false)
    private long numberInvoice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "invoicestate_id")
    private InvoiceState invoiceState;

    public Invoice(LocalDate invoice_date, double import_invoice, long number_invoice, Customer customer, InvoiceState invoiceState) {
        this.invoiceDate = invoice_date;
        this.importInvoice = import_invoice;
        this.numberInvoice = number_invoice;
        this.customer = customer;
        this.invoiceState = invoiceState;
    }


}
