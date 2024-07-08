package clownfiesta.epic_energy_service.services;

import clownfiesta.epic_energy_service.entites.Invoice;
import clownfiesta.epic_energy_service.entites.InvoiceState;
import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.payloads.InvoiceRequestDto;
import clownfiesta.epic_energy_service.repositories.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServices {
    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    InvoiceStateServices invoiceStateServices;

    public Invoice saveInvoice(InvoiceRequestDto body) {
        if (invoiceRepo.existsByNumber_invoice(body.number_invoice())) {
            throw new BadRequestException("Fattura con questo numerazione già esistente");
        }

        InvoiceState invoiceState = invoiceStateServices.findByStatus(body.name_status());

        Invoice invoice = new Invoice();
        invoice.setImport_invoice(body.import_invoice());
        invoice.setInvoice_date(body.invoice_date());
        invoice.setInvoiceState(invoiceState);
        invoice.setNumber_invoice(body.number_invoice());
        invoice.setCustomer(customer);

        return invoiceRepo.save(invoice);
    }
}
