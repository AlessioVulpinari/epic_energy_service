package clownfiesta.epic_energy_service.controllers;

import clownfiesta.epic_energy_service.entites.Invoice;
import clownfiesta.epic_energy_service.payloads.InvoiceRequestDto;
import clownfiesta.epic_energy_service.payloads.NewInvoiceResponseDto;
import clownfiesta.epic_energy_service.services.InvoiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceControllers {
    @Autowired
    private InvoiceServices invoiceServices;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    @ResponseStatus(HttpStatus.CREATED)
    NewInvoiceResponseDto createInvoice(@RequestBody InvoiceRequestDto body) {
        return new NewInvoiceResponseDto(invoiceServices.saveInvoice(body).getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long id) {
        invoiceServices.deleteInvocie(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Invoice updateInvoice(@PathVariable Long id, @RequestBody InvoiceRequestDto body) {
        return invoiceServices.updateInvoice(id, body);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable Long id) {
        return invoiceServices.getInvoiceById(id);
    }

    @GetMapping("/{numberinvoice}")
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceByNumber(@PathVariable Long numberinvoice) {
        return invoiceServices.findBynumberinvoice(numberinvoice);
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoicesByCustomerId(@PathVariable Long customerId) {
        return invoiceServices.getAllInvoicesByCustomerId(customerId);
    }
}
