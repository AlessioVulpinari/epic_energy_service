package clownfiesta.epic_energy_service.controllers;

import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.payloads.AddressRequestDto;
import clownfiesta.epic_energy_service.payloads.NewAddressResponseDto;
import clownfiesta.epic_energy_service.services.AddressServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "http://localhost:5173")
public class AddressController {
    @Autowired
    AddressServices addressServices;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    NewAddressResponseDto createAddress(@RequestBody @Validated AddressRequestDto body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new NewAddressResponseDto(addressServices.saveAddress(body).getId());
    }
}
