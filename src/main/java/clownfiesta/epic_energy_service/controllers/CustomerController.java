package clownfiesta.epic_energy_service.controllers;

import clownfiesta.epic_energy_service.entites.Customer;
import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.payloads.CustomerDTO;
import clownfiesta.epic_energy_service.payloads.CustomerResponseDto;
import clownfiesta.epic_energy_service.services.CustomerService;
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
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    CustomerResponseDto createCustomer(@RequestBody @Validated CustomerDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new CustomerResponseDto(customerService.saveCustomer(body).getId());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Customer updateCustomer(@PathVariable Long id, @Validated @RequestBody CustomerDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return customerService.findByIdAndUpdate(id, body);
    }

    @GetMapping("/name")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> findByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return customerService.orderByname(page, size);
    }

    @GetMapping("/turnover")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> findByAnnualTurnover(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return customerService.orderrByAnnualTurnover(page, size);
    }

    @GetMapping("/date")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> findByInsertionDate(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return customerService.filterByInsertionDate(page, size);
    }

    @GetMapping("/lastcontact")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> findbYLastContact(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return customerService.filterByLastContact(page, size);
    }

    @GetMapping("/provincename")
    Page<Customer> findByProvinceName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return customerService.filterByProvinceName(page, size);
    }

    @GetMapping("/filterturonver/{turnover}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> filterByTurnover(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable long turnover) {
        return customerService.filterByAnnualTurnover(page, size, turnover);
    }

    @GetMapping("/filterinsertiondate/{date}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> filterByInsertionDate(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable LocalDate date) {
        return customerService.filterByInsertionDate(page, size, date);
    }

    @GetMapping("/filterlastdate/{date}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> filterByLastDate(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable LocalDate date) {
        return customerService.filterByLastContactDate(page, size, date);
    }

    @GetMapping("/filtername/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Customer> filterByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String name) {
        return customerService.filterByName(page, size, name);
    }

}
