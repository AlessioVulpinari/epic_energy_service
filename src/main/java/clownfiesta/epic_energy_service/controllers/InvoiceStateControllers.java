package clownfiesta.epic_energy_service.controllers;

import clownfiesta.epic_energy_service.entites.InvoiceState;
import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.payloads.InvoiceStateRequestDTO;
import clownfiesta.epic_energy_service.payloads.NewInvoiceStateResponseDTO;
import clownfiesta.epic_energy_service.services.InvoiceStateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/states")
@CrossOrigin(origins = "http://localhost:5173")
public class InvoiceStateControllers {
    @Autowired
    InvoiceStateServices invoiceStateServices;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    NewInvoiceStateResponseDTO createState(@RequestBody @Validated InvoiceStateRequestDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new NewInvoiceStateResponseDTO(invoiceStateServices.saveState(body).getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteState(@PathVariable Long id) {
        invoiceStateServices.deleteStatus(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    InvoiceState updateState(@PathVariable Long id, @RequestBody @Validated InvoiceStateRequestDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return invoiceStateServices.updateState(id, body);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceState getStateById(@PathVariable Long id) {
        return invoiceStateServices.getStateById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceState> getAllStates() {
        return invoiceStateServices.getAllStates();
    }
}
