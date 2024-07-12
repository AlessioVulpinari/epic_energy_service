package clownfiesta.epic_energy_service.services;

import clownfiesta.epic_energy_service.entites.Customer;
import clownfiesta.epic_energy_service.enums.ClientType;
import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.excepitions.NotFoundException;
import clownfiesta.epic_energy_service.payloads.CustomerDTO;
import clownfiesta.epic_energy_service.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepository;

    public Page<Customer> getCustomers(int page, int pageSize) {
        if (pageSize <= 0) pageSize = 10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable = PageRequest.of(page, pageSize);
        return customerRepository.findAll(pageable);
    }

    public Customer getUser(long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) return customer.get();
        else throw new NotFoundException("Cliente con questo id: " + id + " non trovato!");
    }

    public Customer findById(long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException(customerId));
    }


    public Customer findByIdAndUpdate(long customerId, CustomerDTO body) {
        Customer found = findById(customerId);

        found.setBusinessName(body.businessName());
        found.setVatNumber(body.vatNumber());
        found.setEmail(body.email());
        found.setDateLastContact(body.dateLastContact());
        found.setAnnualTurnover(body.annualTurnover());
        found.setPecCustomer(body.customerPec());
        found.setTelCustomer(body.telCustomer());
        found.setEmailContact(body.emailContact());
        found.setSurnameContact(body.surnameContact());
        found.setNameContact(body.nameContact());
        found.setTelContact(body.telContact());
        found.setLogoAgency(body.logoAgency());
        found.setClientType(ClientType.valueOf(body.clientType()));

        return customerRepository.save(found);
    }

    public void findByIdAndDelete(long userId) {
        Customer found = findById(userId);
        this.customerRepository.delete(found);
    }

    public Customer saveCustomer(CustomerDTO body) {
        if (this.customerRepository.existsByVatNumber(body.vatNumber()))
            throw new BadRequestException("Esiste già un cliente con questa partita IVA!");
        if (this.customerRepository.existsByBusinessNameAndClientType(body.businessName(), ClientType.valueOf(body.clientType()))) {
            throw new BadRequestException("Esiste già un cliente con questa denominazione!");
        }

        Customer customer = new Customer(body.businessName(), body.vatNumber(), body.email(), body.dateLastContact(),
                body.annualTurnover(), body.customerPec(), body.telCustomer(), body.emailContact(), body.nameContact(),
                body.surnameContact(), body.telContact(), body.logoAgency(), ClientType.valueOf(body.clientType()));

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Page<Customer> orderByname(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.orderByBusinessName(pageable);
    }

    public Page<Customer> orderrByAnnualTurnover(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.orderByAnnualTurnover(pageable);
    }

    public Page<Customer> filterByLastContact(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.orderByDateLastContact(pageable);
    }

    public Page<Customer> filterByInsertionDate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.orderByInsertionDate(pageable);
    }

    /*public Page<Customer> filterByLegalOffice(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.orderByProvinceName(pageable);
    }*/

    public Page<Customer> filterByAnnualTurnover(int page, int size, long turnover) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.filterByTurnover(turnover, pageable);
    }

    public Page<Customer> filterByInsertionDate(int page, int size, LocalDate date) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.filterByInsertionDater(date, pageable);
    }

    public Page<Customer> filterByLastContactDate(int page, int size, LocalDate date) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.filterByLastContactDate(date, pageable);
    }

    public Page<Customer> filterByName(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.filterByName(name, pageable);
    }
}
