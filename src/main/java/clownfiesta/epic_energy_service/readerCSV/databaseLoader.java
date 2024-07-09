package clownfiesta.epic_energy_service.readerCSV;

import clownfiesta.epic_energy_service.entites.City;
import clownfiesta.epic_energy_service.entites.Province;
import clownfiesta.epic_energy_service.services.CityServices;
import clownfiesta.epic_energy_service.services.ProvinceServices;
import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@Component
public class databaseLoader {

    @Autowired
    public CSVReader CSVReader;

    @Autowired
    public CityServices cityServices;

    @Autowired
    public ProvinceServices provinceServices;

    @PostConstruct
    public void iniettaAddress() throws Exception {
        List<Province> existingProvinces = provinceServices.findAllProvince();
        List<City> existingMunicipalities = cityServices.findAllCity();

        Path csvPath1 = Paths.get("src/test/java/clownfiesta/epic_energy_service/CSV/comuni-italiani.csv");
        Path csvPath2 = Paths.get("src/test/java/clownfiesta/epic_energy_service/CSV/province-italiane.csv");


        List<String[]> csvProvincia = CSVReader.readCsv(csvPath2);
        List<String[]> csvCity = CSVReader.readCsv(csvPath1);

        List<Province> newProvinces = csvProvincia.stream()
                .map(row -> {
                    Province province = new Province();
                    province.setInitial_province(row[0]);
                    province.setName_province(row[1]);
                    province.setRegion_province(row[2]);
                    return province;
                })
                .collect(Collectors.toList());

        List<City> newCity = csvCity.stream()
                .map(row -> {
                    City city = new City();
                    city.setProgressive_city(String.valueOf(row[0]) + String.valueOf(row[1]));
                    city.setDenomination_city(row[2]);
                    city.setProvince(row[3]);
                    return city;
                })
                .collect(Collectors.toList());

        Set<Province> newProvincesSet = new HashSet<>(newProvinces);
        newProvincesSet.removeAll(existingProvinces);

        Set<City> newMunicipalitiesSet = new HashSet<>(newCity);
        newMunicipalitiesSet.removeAll(existingMunicipalities);

        newProvincesSet.forEach(provinceDAO::save);
        newMunicipalitiesSet.forEach(cityDAO::save);
    }

}
