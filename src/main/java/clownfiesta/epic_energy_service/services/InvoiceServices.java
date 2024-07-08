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

    public Invoice saveInvoice(InvoiceRequestDto body) {
        if (invoiceRepo.existsByNumber_invoice(body.number_invoice())) {
            throw new BadRequestException("Fattura con questo numero già esistente");
        }

        InvoiceState invoiceState = invoiceStateServices.findByStatus(body.name_status());
        Customer customer = customerService.findCustomerById(body.customer_id());

        Invoice invoice = new Invoice();
        invoice.setImport_invoice(body.import_invoice());
        invoice.setInvoice_date(body.invoice_date());
        invoice.setInvoiceState(invoiceState);
        invoice.setNumber_invoice(body.number_invoice());
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
        return invoiceRepo.existsByNumber_invoice(number_invoice).orElseThrow(() -> new NotFoundException("fattura non trovata"));
    }

    public Invoice updateState(Long id, InvoiceRequestDto body) {
        Invoice invoice = getInvoiceById(id);

        InvoiceState invoiceState = invoiceStateServices.findByStatus(body.name_status());

        invoice.setImport_invoice(body.import_invoice());
        invoice.setInvoice_date(body.invoice_date());
        invoice.setInvoiceState(invoiceState);
        invoice.setNumber_invoice(body.number_invoice());
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
}
