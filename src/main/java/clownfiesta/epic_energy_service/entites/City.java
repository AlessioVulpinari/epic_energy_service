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
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private Province province;

    private String denomination_city;
    private String progressive_city;

    public City(Province province, String denomination_city, String progressive_city) {
        this.province = province;
        this.denomination_city = denomination_city;
        this.progressive_city = progressive_city;
    }
}
