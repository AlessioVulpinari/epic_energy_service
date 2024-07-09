package clownfiesta.epic_energy_service.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indirizzo", nullable = false)
    private Long id;

    @Column(name = "via", nullable = false)
    private String street;

    @Column(name = "civico", nullable = false)
    private int houseNumber;

    @Column(name = "località", nullable = false)
    private String locality;

    @Column(nullable = false)
    private long cap;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City cityResidence;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Address(String street, int houseNumber, String locality, long cap, City cityResidence, Customer customer) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.locality = locality;
        this.cap = cap;
        this.cityResidence = cityResidence;
        this.customer = customer;
    }
}
