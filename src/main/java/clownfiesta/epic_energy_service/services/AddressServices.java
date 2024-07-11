package clownfiesta.epic_energy_service.services;

import clownfiesta.epic_energy_service.entites.Address;
import clownfiesta.epic_energy_service.enums.LocationType;
import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.payloads.AddressRequestDto;
import clownfiesta.epic_energy_service.repositories.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServices {
    @Autowired
    AddressRepo addressRepo;

    @Autowired
    CustomerService customerService;

    public List<Address> findByCustomer(Long customerId) {
        return this.addressRepo.filterByCustomer(customerId);
    }

//    public boolean checkAddressExist(long customerId, LocationType locationType) {
//        return addressRepo.filterByCustomerAndExistByLocationType(customerId, locationType).orElse(false);
//    }

    public Address saveAddress(AddressRequestDto body) {

        if (addressRepo.existsByStreetAndLocality(body.street(), body.locality())) {
            throw new BadRequestException("Indirizzo associato a questo customer già esistente");
        }

        if ( this.addressRepo.filterByCustomer(body.customer().getId()).size() == 2) {
            throw new BadRequestException("Il cliente possiede già due indirizzi!");
        }

        if (!this.addressRepo.filterByCustomerAndExistByLocationType(body.customer().getId(), LocationType.valueOf(body.locationType())).isEmpty()) {
            throw new BadRequestException("Esiste già una sede di tipo: " + body.locationType());
        }

        Address address = new Address();

        address.setCustomer(body.customer());
        address.setCap(body.cap());
        address.setLocality(body.locality());
        address.setStreet(body.street());
        address.setHouseNumber(body.houseNumber());
        address.setCityResidence(body.cityResidence());
        address.setLocationTypes(LocationType.valueOf(body.locationType()));


        return addressRepo.save(address);
    }
}
