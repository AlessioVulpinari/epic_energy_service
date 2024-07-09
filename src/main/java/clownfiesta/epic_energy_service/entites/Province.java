package clownfiesta.epic_energy_service.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String initial_province;
    private String name_province;
    private String region_province;

    public Province(String initial_province, String name_province, String region_province) {
        this.initial_province = initial_province;
        this.name_province = name_province;
        this.region_province = region_province;
    }
}
