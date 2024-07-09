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
    private Province district;

    private String denominationCity;
    private String progressiveCity;

    public City(Province district, String denomination_city, String progressive_city) {
        this.district = district;
        this.denominationCity = denomination_city;
        this.progressiveCity = progressive_city;
    }
}
