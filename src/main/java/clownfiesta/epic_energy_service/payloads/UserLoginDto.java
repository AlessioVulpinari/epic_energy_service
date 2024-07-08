package clownfiesta.epic_energy_service.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDto(@Email @NotBlank String email, @NotBlank String password) {
}
