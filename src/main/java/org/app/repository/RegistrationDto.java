package org.app.repository;


import lombok.Data;

@Data
public class RegistrationDto {
    private String username;
    private String password;
    private String repeatPassword;
}
