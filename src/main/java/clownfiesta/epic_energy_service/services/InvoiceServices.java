package clownfiesta.epic_energy_service.services;

import clownfiesta.epic_energy_service.entites.Customer;
import clownfiesta.epic_energy_service.entites.Invoice;
import clownfiesta.epic_energy_service.entites.InvoiceState;
import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.excepitions.NotFoundException;
import clownfiesta.epic_energy_service.payloads.InvoiceRequestDto;
import clownfiesta.epic_energy_service.repositories.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServices {
    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    InvoiceStateServices invoiceStateServices;

    @Autowired
    CustomerService customerService;

    public Invoice saveInvoice(InvoiceRequestDto body) {
        if (invoiceRepo.existsByNumberInvoice(body.number_invoice())) {
            throw new BadRequestException("Fattura con questo numero già esistente");
        }

        InvoiceState invoiceState = invoiceStateServices.findByStatus(body.name_status());
        Customer customer = customerService.findById(body.customer_id());

        Invoice invoice = new Invoice();
        invoice.setImportInvoice(body.import_invoice());
        invoice.setInvoiceDate(body.invoice_date());
        invoice.setInvoiceState(invoiceState);
        invoice.setNumberInvoice(body.number_invoice());
        invoice.setCustomer(customer);

        return invoiceRepo.save(invoice);
    }


    public Invoice getInvoiceById(long id) {
        Optional<Invoice> optionalInvoice = invoiceRepo.findById(id);

        if (optionalInvoice.isPresent()) {
            return optionalInvoice.get();
        } else {
            throw new NotFoundException("Fattura con questo id non trovata");
        }
    }

    public Invoice findBynumberinvoice(long number_invoice) {
        return invoiceRepo.findByNumberInvoice(number_invoice).orElseThrow(() -> new NotFoundException("fattura non trovata"));
    }

    public Invoice updateInvoice(Long id, InvoiceRequestDto body) {
        Invoice invoice = getInvoiceById(id);

        InvoiceState invoiceState = invoiceStateServices.findByStatus(body.name_status());
        Customer customer = customerService.findById(body.customer_id());

        invoice.setImportInvoice(body.import_invoice());
        invoice.setInvoiceDate(body.invoice_date());
        invoice.setInvoiceState(invoiceState);
        invoice.setNumberInvoice(body.number_invoice());
        invoice.setCustomer(customer);

        return invoiceRepo.save(invoice);
    }

    public void deleteInvocie(Long id) {
        Invoice invoice = getInvoiceById(id);
        invoiceRepo.delete(invoice);
    }

    public List<Invoice> getAllInvoice() {
        return invoiceRepo.findAll();
    }

    public List<Invoice> getAllInvoicesByCustomerId(Long customerId) {
        Customer customer = customerService.findById(customerId);
        return invoiceRepo.findByCustomer(customer);
    }
}
