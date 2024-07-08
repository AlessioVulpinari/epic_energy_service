package clownfiesta.epic_energy_service.payloads;

import jakarta.validation.constraints.NotEmpty;

public record InvoiceStateRequestDto(
        @NotEmpty(message = "Inserire uno stato fattura")
        String status_name
) {
}
