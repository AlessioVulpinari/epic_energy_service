package clownfiesta.epic_energy_service.payloads;

import jakarta.validation.constraints.NotBlank;

public record UserLoginResponseDTO(@NotBlank String tokenId) {
}
