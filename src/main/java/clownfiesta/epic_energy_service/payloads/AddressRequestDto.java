package clownfiesta.epic_energy_service.payloads;

import clownfiesta.epic_energy_service.entites.City;
import clownfiesta.epic_energy_service.entites.Customer;
import jakarta.validation.constraints.NotNull;

public record AddressRequestDto(
        @NotNull(message = "Inserire un indirizzo")
        String street,
        @NotNull(message = "Inserire civico")
        Integer houseNumber,
        @NotNull(message = "Inserire località")
        String locality,
        @NotNull(message = "Inserire cap")
        Long cap,
        @NotNull(message = "Inserire città di residenza")
        City cityResidence,
        @NotNull(message = "Inserire customer")
        Customer customer,
        @NotNull(message = "Inserire tipo sede")
        String locationType
) {
}
