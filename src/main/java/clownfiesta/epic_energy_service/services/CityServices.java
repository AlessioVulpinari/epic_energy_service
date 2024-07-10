package clownfiesta.epic_energy_service.services;

import clownfiesta.epic_energy_service.entites.City;
import clownfiesta.epic_energy_service.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServices {

    @Autowired
    CityRepository cityRepository;

    public List<City> findAllCity (){
        return cityRepository.findAll();
    }
}
