package org.app.service;

import org.app.model.User;
import org.app.repository.RegistrationDto;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserService userService;

    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public boolean isValidRegistration(RegistrationDto registrationDto){
        // here we should place the logic for data validation
        // in registerSubmit()

        // 1. Check if user exists already -- REWRITE THIS METHOD, ADD SEARCH BY USERNAME
        for (User user : userService.getUsers()){
            if (registrationDto.getUsername().equals(user.getLogin())){
//                throw new IllegalStateException("The user already exists!"); //here, we should redirect to another page
                return false;
            }
        }

        // 2. Check if the entered passwords match
        String hashed = BCrypt.hashpw(registrationDto.getPassword(), BCrypt.gensalt());
        String candidate = registrationDto.getRepeatPassword();

        if (!BCrypt.checkpw(candidate,hashed)){
            return false;
        }
        return true;
    }
}
