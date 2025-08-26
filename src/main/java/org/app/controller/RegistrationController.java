package org.app.controller;

import org.app.model.User;
import org.app.repository.RegistrationDto;
import org.app.service.RegistrationService;
import org.app.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final RegistrationService registrationService;


    public RegistrationController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }


    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registrationDto", new RegistrationDto());
        return "sign-up";
    }


    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute RegistrationDto registrationDto){
        // Data validation
        // 1. Check if user exists already
        // 2. Check if the entered passwords match

        // If validation is passed:
            // - Create new user
            // - Hash password
            // - Persist user
            // - Redirect to the login page

        if (registrationService.isValidRegistration(registrationDto)){
                User user = new User();
                user.setLogin(registrationDto.getUsername());
                user.setPassword(BCrypt.hashpw(registrationDto.getPassword(), BCrypt.gensalt()));
                userService.addUser(user);
                return "sign-in";

            }
        else{
            return "sign-up-with-errors";
        }
    }
}
