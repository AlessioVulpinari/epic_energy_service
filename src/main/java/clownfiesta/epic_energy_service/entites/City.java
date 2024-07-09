package clownfiesta.epic_energy_service.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comuni")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private Province district;

    @Column(name = "denominazione", nullable = false)
    private String denominationCity;

    @Column(name = "progressivo_citta", nullable = false)
    private String progressiveCity;

    public City(Province district, String denominationCity, String progressiveCity) {
        this.district = district;
        this.denominationCity = denominationCity;
        this.progressiveCity = progressiveCity;
    }
}
