package clownfiesta.epic_energy_service.services;

import clownfiesta.epic_energy_service.entites.Province;
import clownfiesta.epic_energy_service.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServices {
    @Autowired
    ProvinceRepository provinceRepository;

    public List<Province> findAllProvince (){
        return provinceRepository.findAll();
    }
    public Province findByName (String nameDistrict){
        System.out.println(nameDistrict);
        return provinceRepository.findByNameDistrictLike(nameDistrict).orElse( null);
    }
}
