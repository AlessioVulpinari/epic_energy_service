package clownfiesta.epic_energy_service.readerCSV;

import clownfiesta.epic_energy_service.entites.City;
import clownfiesta.epic_energy_service.entites.Province;
import clownfiesta.epic_energy_service.excepitions.NotFoundException;
import clownfiesta.epic_energy_service.repositories.CityRepository;
import clownfiesta.epic_energy_service.repositories.ProvinceRepository;
import clownfiesta.epic_energy_service.services.CityServices;
import clownfiesta.epic_energy_service.services.ProvinceServices;
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
                    switch (row[1]) {

                        case "Monza-Brianza":
                            row[1] = "Monza e della Brianza";
                            break;

                        case "Vibo-Valentia":
                            row[1] = "Vibo Valentia";
                            break;

                        case "La-Spezia":
                            row[1] = "La Spezia";
                            break;

                        case "Aosta":
                            row[1] = "Valle d'Aosta/Vallée d'Aoste";
                            break;

                        case "Ascoli-Piceno":
                            row[1] = "Ascoli Piceno";
                            break;

                        case "Bolzano":
                            row[1] = "Bolzano/Bozen";
                            break;

                        case "Pesaro-Urbino":
                            row[1] = "Pesaro e Urbino";
                            break;

                        case "Reggio-Calabria":
                            row[1] = "Reggio Calabria";
                            break;

                        case "Forli-Cesena":
                            row[1] = "Forlì-Cesena";
                            break;

                        case "Reggio-Emilia":
                            row[1] = "Reggio nell'Emilia";
                            break;

                        default:
                            break;
                    }

                    return new Province(row[0], row[1], row[2]);
                })
                .collect(Collectors.toList());

        Province verbano = new Province("VCO", "Verbano-Cusio-Ossola", "Piemonte");
        Province sudSardegna = new Province("SU", "Sud Sardegna", "Sardegna");

        newProvinces.add(verbano);
        newProvinces.add(sudSardegna);

        Set<Province> newProvincesSet = new HashSet<>(newProvinces);
        newProvincesSet.removeAll(existingProvinces);

        // newProvincesSet.forEach(provinceRepository::save);

        HashSet<String> errorList = new HashSet<>();

        List<City> newCity = csvCity.stream()
                .map(row -> {
                    City city = new City();

                    try {
                        city.setProgressiveCity(row[0] + row[1]);
                        city.setDenominationCity(row[2]);
                        city.setDistrict(this.provinceServices.findByName(row[3]));

                    } catch (NotFoundException e) {
                        errorList.add(row[3]);
                    }

                    return city;
                })
                .collect(Collectors.toList());


        Set<City> newMunicipalitiesSet = new HashSet<>(newCity);
        newMunicipalitiesSet.removeAll(existingMunicipalities);

       // newMunicipalitiesSet.forEach(cityRepository::save);

        //errorList.forEach(System.out::println);
    }

}
