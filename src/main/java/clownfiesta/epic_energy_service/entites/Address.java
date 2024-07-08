package clownfiesta.epic_energy_service.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private int house_number;
    private String locality;
    private long cap;

    @ManyToOne
    @JoinColumn(name = "cityresidence_id")
    private City cityResidence;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Address(String street, int house_number, String locality, long cap, City cityResidence, Customer customer) {
        this.street = street;
        this.house_number = house_number;
        this.locality = locality;
        this.cap = cap;
        this.cityResidence = cityResidence;
        this.customer = customer;
    }
}
