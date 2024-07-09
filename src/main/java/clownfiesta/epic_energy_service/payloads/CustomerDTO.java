package clownfiesta.epic_energy_service.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerDTO(@NotBlank String businessName,
                          @NotBlank String vatNumber,
                          @NotBlank @Email String email,
                          @NotNull LocalDate dateLastContact,
                          @NotNull long annualTurnover,
                          @NotBlank @Email String customerPec,
                          @NotNull long telCustomer,
                          @NotBlank @Email String emailContact,
                          @NotBlank String nameContact,
                          @NotBlank String surnameContact,
                          @NotNull long telContact,
                          @NotBlank String logoAgency,
                          @NotBlank String clientType)
{ }
