package clownfiesta.epic_energy_service.payloads;

import jakarta.validation.constraints.NotNull;

public record NewInvoiceStateResponseDTO(@NotNull Long id) {
}
