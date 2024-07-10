package clownfiesta.epic_energy_service.services;

import clownfiesta.epic_energy_service.entites.Address;
import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.payloads.AddressRequestDto;
import clownfiesta.epic_energy_service.repositories.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServices {
    @Autowired
    AddressRepo addressRepo;

    public Address saveAddress(AddressRequestDto body) {
        if (addressRepo.existsByStreetAndLocality(body.street(), body.locality())) {
            throw new BadRequestException("Indirizzo associato a questo customer già esistente");
        }

        Address address = new Address();

        address.setCustomer(body.customer());
        address.setCap(body.cap());
        address.setLocality(body.locality());
        address.setStreet(body.street());
        address.setHouseNumber(body.houseNumber());
        address.setCityResidence(body.cityResidence());

        return addressRepo.save(address);
    }
}
