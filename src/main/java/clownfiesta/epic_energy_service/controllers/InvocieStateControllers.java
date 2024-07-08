package clownfiesta.epic_energy_service.controllers;

import clownfiesta.epic_energy_service.entites.InvoiceState;
import clownfiesta.epic_energy_service.payloads.InvoiceStateRequestDto;
import clownfiesta.epic_energy_service.payloads.NewInvocieStateResponseDto;
import clownfiesta.epic_energy_service.services.InvoiceStateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/state")
public class InvocieStateControllers {
    @Autowired
    InvoiceStateServices invoiceStateServices;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    NewInvocieStateResponseDto createState(@RequestBody InvoiceStateRequestDto body) {
        return new NewInvocieStateResponseDto(invoiceStateServices.saveState(body).getId());
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
    InvoiceState updateState(@PathVariable Long id, @RequestBody InvoiceStateRequestDto body) {
        return invoiceStateServices.updateState(id, body);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceState getStateById(@PathVariable Long id) {
        return invoiceStateServices.getStateById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceState> getAllStates() {
        return invoiceStateServices.getAllStates();
    }
}
