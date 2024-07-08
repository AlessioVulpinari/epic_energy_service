package clownfiesta.epic_energy_service.controllers;

import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.payloads.UserLoginResponseDTO;
import clownfiesta.epic_energy_service.payloads.UserRegistrationResponseDTO;
import clownfiesta.epic_energy_service.payloads.UserLoginDto;
import clownfiesta.epic_energy_service.payloads.UserRequiredDTO;
import clownfiesta.epic_energy_service.services.AuthServices;
import clownfiesta.epic_energy_service.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthServices authServices;

    @Autowired
    private UserServices userServices;

    @PostMapping("/user/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDto body) {
        return new UserLoginResponseDTO(authServices.generateToken(body));
    }

    @PostMapping("/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegistrationResponseDTO registerUser(@RequestBody @Validated UserRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }

        return new UserRegistrationResponseDTO(this.userServices.saveUser(body).getId());
    }
}
