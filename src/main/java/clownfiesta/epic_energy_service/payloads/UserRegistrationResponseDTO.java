package clownfiesta.epic_energy_service.payloads;

import jakarta.validation.constraints.NotNull;

public record UserRegistrationResponseDTO(@NotNull long userId) {
}
