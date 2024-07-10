package clownfiesta.epic_energy_service.controllers;

import clownfiesta.epic_energy_service.entites.Invoice;
import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.payloads.InvoiceRequestDTO;
import clownfiesta.epic_energy_service.payloads.NewInvoiceResponseDTO;
import clownfiesta.epic_energy_service.services.InvoiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/invoices")
public class InvoiceControllers {
    @Autowired
    private InvoiceServices invoiceServices;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    NewInvoiceResponseDTO createInvoice(@RequestBody @Validated InvoiceRequestDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new NewInvoiceResponseDTO(invoiceServices.saveInvoice(body).getId());
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
    Invoice updateInvoice(@PathVariable Long id, @Validated @RequestBody InvoiceRequestDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
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
    public Invoice getInvoiceByNumber(@PathVariable Long numberInvoice) {
        return invoiceServices.findBynumberinvoice(numberInvoice);
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoicesByCustomerId(@PathVariable Long customerId) {
        return invoiceServices.getAllInvoicesByCustomerId(customerId);
    }

    @GetMapping("/filtercustomer/{customername}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Invoice> filterByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @PathVariable String customername) {
        return invoiceServices.filterByCustomerName(page, size, customername);
    }

    @GetMapping("/filterdate/{date}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Invoice> filterByDate(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @PathVariable LocalDate date) {
        return invoiceServices.filterByDate(page, size, date);
    }

    @GetMapping("/filterstate/{state}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Invoice> filterByState(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @PathVariable String state) {
        return invoiceServices.filterByState(page, size, state);
    }
}
