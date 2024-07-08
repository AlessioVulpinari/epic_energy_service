package clownfiesta.epic_energy_service.entites;

import clownfiesta.epic_energy_service.enums.ClientType;
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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;
    private String vatNumber;
    private String email;
    private LocalDate insertionDate;
    private LocalDate dateLastContact;
    private long annualTurnover;
    private String pecCustomer;
    private long telCustomer;
    private String emailContact;
    private String nameContact;
    private String surnameContact;
    private long telContact;
    private String logoAgency;

    @Enumerated(EnumType.STRING)
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
