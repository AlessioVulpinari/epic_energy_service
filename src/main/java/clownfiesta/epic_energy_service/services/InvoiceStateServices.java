package clownfiesta.epic_energy_service.services;

import clownfiesta.epic_energy_service.entites.InvoiceState;
import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.excepitions.NotFoundException;
import clownfiesta.epic_energy_service.payloads.InvoiceStateRequestDto;
import clownfiesta.epic_energy_service.repositories.InvoiceStateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceStateServices {
    @Autowired
    InvoiceStateRepo invoiceStateRepo;

    public InvoiceState saveState(InvoiceStateRequestDto body) {
        if (invoiceStateRepo.existsByStatus_name(body.status_name())) {
            throw new BadRequestException("Stato gia esistente");
        }
        InvoiceState invoiceState = new InvoiceState();
        invoiceState.setStatus_name(body.status_name());

        return invoiceStateRepo.save(invoiceState);
    }

    public InvoiceState getStateById(long id) {
        Optional<InvoiceState> oprtionalState = invoiceStateRepo.findById(id);

        if (oprtionalState.isPresent()) {
            return oprtionalState.get();
        } else {
            throw new NotFoundException("Stato con questo id non trovato");
        }
    }

    public InvoiceState findByStatus(String status) {
        return invoiceStateRepo.findByStatus_name(status).orElseThrow(() -> new NotFoundException("stato non trovato"));
    }

    public InvoiceState updateState(Long id, InvoiceStateRequestDto body) {
        InvoiceState invoiceState = getStateById(id);

        invoiceState.setStatus_name(body.status_name());

        return invoiceStateRepo.save(invoiceState);
    }

    public void deleteStatus(Long id) {
        InvoiceState invoiceState = getStateById(id);
        invoiceStateRepo.delete(invoiceState);
    }

    public List<InvoiceState> getAllStates() {
        return invoiceStateRepo.findAll();
    }
}
