package clownfiesta.epic_energy_service.readerCSV;

import clownfiesta.epic_energy_service.entites.City;
import clownfiesta.epic_energy_service.entites.Province;
import clownfiesta.epic_energy_service.repositories.CityRepository;
import clownfiesta.epic_energy_service.repositories.ProvinceRepository;
import clownfiesta.epic_energy_service.services.CityServices;
import clownfiesta.epic_energy_service.services.ProvinceServices;
import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class databaseLoader {

    @Autowired
    public CSVreader CSVreader;

    @Autowired
    public CityServices cityServices;

    @Autowired
    public ProvinceServices provinceServices;

    @Autowired
    public CityRepository cityRepository;

    @Autowired
    public ProvinceRepository provinceRepository;


    @PostConstruct
    public void iniettaAddress() throws Exception {
        List<Province> existingProvinces = provinceServices.findAllProvince();
        List<City> existingMunicipalities = cityServices.findAllCity();

        Path csvPath1 = Paths.get("src/main/java/clownfiesta/epic_energy_service/CSV/comuni-italiani.csv");
        Path csvPath2 = Paths.get("src/main/java/clownfiesta/epic_energy_service/CSV/province-italiane.csv");


        List<String[]> csvProvincia = CSVreader.readCsv(csvPath2);
        List<String[]> csvCity = CSVreader.readCsv(csvPath1);

        List<Province> newProvinces = csvProvincia.stream()
                .map(row -> {
                    Province province = new Province();
                    province.setInitialDistrict(row[0]);
                    province.setNameDistrict(row[1]);
                    province.setRegionDistrict(row[2]);
                    return province;
                })
                .collect(Collectors.toList());

        Set<Province> newProvincesSet = new HashSet<>(newProvinces);
        newProvincesSet.removeAll(existingProvinces);

        newProvincesSet.forEach(provinceRepository::save);

        List<City> newCity = csvCity.stream()
                .map(row -> {
                    City city = new City();
                    city.setProgressiveCity(String.valueOf(row[0]) + String.valueOf(row[1]));
                    city.setDenominationCity(row[2]);
//                    city.setDistrict(this.provinceServices.findByName(row[3])); //commentando questo e run si riempiono entrambe le tabelle ma nelle city la colonna district_id rimane vuota
//                    System.out.println((row[3]));
                    return city;
                })
                .collect(Collectors.toList());



        Set<City> newMunicipalitiesSet = new HashSet<>(newCity);
        newMunicipalitiesSet.removeAll(existingMunicipalities);


        newMunicipalitiesSet.forEach(cityRepository::save);
    }

}
