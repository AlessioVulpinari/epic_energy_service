package clownfiesta.epic_energy_service.entites;

import clownfiesta.epic_energy_service.enums.ClientType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Long id;

    @Column(name = "nome_azienda", nullable = false)
    private String businessName;

    @Column(name = "partita_iva", nullable = false)
    private String vatNumber;

    @Column(nullable = false)
    private String email;

    @Column(name = "data_inserimento", nullable = false)
    private LocalDate insertionDate;

    @Column(name = "data_ultimo_contatto", nullable = false)
    private LocalDate dateLastContact;

    @Column(name = "fatturato_annuale", nullable = false)
    private long annualTurnover;

    @Column(name = "pec_aziendale", nullable = false)
    private String pecCustomer;

    @Column(name = "numero_aziendale", nullable = false)
    private long telCustomer;

    @Column(name = "email_contatto", nullable = false)
    private String emailContact;

    @Column(name = "nome_contatto", nullable = false)
    private String nameContact;

    @Column(name = "cognome_contatto", nullable = false)
    private String surnameContact;

    @Column(name = "telefono_contatto", nullable = false)
    private long telContact;

    @Column(name = "logo_azienda", nullable = false)
    private String logoAgency;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_azienda", nullable = false)
    private ClientType clientType;

    public Customer(String businessName, String vatNumber, String email,
                    LocalDate dateLastContact, long annualTurnover, String pecCustomer,
                    long telCustomer, String emailContact, String nameContact,
                    String surnameContact, long telContact, String logoAgency,
                    ClientType clientType) {
        this.businessName = businessName;
        this.vatNumber = vatNumber;
        this.email = email;
        this.insertionDate = LocalDate.now();
        this.dateLastContact = dateLastContact;
        this.annualTurnover = annualTurnover;
        this.pecCustomer = pecCustomer;
        this.telCustomer = telCustomer;
        this.emailContact = emailContact;
        this.nameContact = nameContact;
        this.surnameContact = surnameContact;
        this.telContact = telContact;
        this.logoAgency = logoAgency;
        this.clientType = clientType;
    }
}
