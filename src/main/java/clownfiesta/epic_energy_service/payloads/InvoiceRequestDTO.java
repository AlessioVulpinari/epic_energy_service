package clownfiesta.epic_energy_service.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InvoiceRequestDTO(
        @NotNull(message = "Inserire data fattura")
        LocalDate invoice_date,
        @NotNull(message = "Inserire importo fattura")
        Double import_invoice,
        @NotNull(message = "Inserire numero fattura")
        Long number_invoice,
        @NotNull(message = "Inserire cliente")
        Long customer_id,
        @NotNull(message = "Inserire stato fattura")
        String name_status
) {
}
