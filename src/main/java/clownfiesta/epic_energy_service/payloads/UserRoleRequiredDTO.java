package clownfiesta.epic_energy_service.payloads;

import jakarta.validation.constraints.NotBlank;

public record UserRoleRequiredDTO(@NotBlank String name) {
}
