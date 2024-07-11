package clownfiesta.epic_energy_service.controllers;

import clownfiesta.epic_energy_service.entites.User;
import clownfiesta.epic_energy_service.excepitions.BadRequestException;
import clownfiesta.epic_energy_service.payloads.UserRequiredDTO;
import clownfiesta.epic_energy_service.payloads.UserRoleRequiredDTO;
import clownfiesta.epic_energy_service.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/me")
    public User getMyProfile(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    @PutMapping("/me")
    public User updateMyProfile(@AuthenticationPrincipal User currentUser, @RequestBody @Validated UserRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.userServices.findByIdAndUpdate(currentUser.getId(), body);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.userServices.getUsers(page, size);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable long userId) {
        return this.userServices.findById(userId);
    }

    @PatchMapping("/{userId}/roles/add")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public User addRole(@PathVariable long userId, @RequestBody @Validated UserRoleRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.userServices.addRole(userId, body);
    }

    @PatchMapping("/{userId}/roles/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User removeRole(@PathVariable long userId, @RequestBody @Validated UserRoleRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.userServices.removeRole(userId, body);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByIdAndUpdate(@PathVariable long userId, @RequestBody @Validated UserRequiredDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.userServices.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long userId) {
        this.userServices.findByIdAndDelete(userId);
    }
}
