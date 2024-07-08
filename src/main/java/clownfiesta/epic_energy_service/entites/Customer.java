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

    private String bussines_name;
    private String vat_number;
    private String email;
    private LocalDate insertion_date;
    private LocalDate date_last_contact;
    private long annual_turnover;
    private String pec_customer;
    private long tel_customer;
    private String email_contact;
    private String name_contact;
    private String sruname_contact;
    private long tel_contact;
    private String logo_agency;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    public Customer(String bussines_name, String vat_number, String email, LocalDate insertion_date, LocalDate date_last_contact, long annual_turnover, String pec_customer, long tel_customer, String email_contact, String name_contact, String sruname_contact, long tel_contact, String logo_agency, ClientType clientType) {
        this.bussines_name = bussines_name;
        this.vat_number = vat_number;
        this.email = email;
        this.insertion_date = insertion_date;
        this.date_last_contact = date_last_contact;
        this.annual_turnover = annual_turnover;
        this.pec_customer = pec_customer;
        this.tel_customer = tel_customer;
        this.email_contact = email_contact;
        this.name_contact = name_contact;
        this.sruname_contact = sruname_contact;
        this.tel_contact = tel_contact;
        this.logo_agency = logo_agency;
        this.clientType = clientType;
    }
}
