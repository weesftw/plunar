package com.weesftw.scaffold.api.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDTO
{
    @NotBlank(message = "Field name can't be blank.")
    private String firstName;
    @NotBlank(message = "Field name can't be blank.")
    private String lastName;
    @Email(message = "E-mail invalid.")
    @NotBlank(message = "Field can't be blank.")
    private String email;
    @NotBlank(message = "Field can't be blank.")
    private String phoneNumber;
    @NotBlank(message = "Field can't be blank.")
    private String zipCode;

    @NotBlank(message = "Username cannot be empty.")
    @Size(min = 5, message = "Username must be greater than five characters.")
    private String username;
    @Size(min = 5, message = "Password is weak.")
    @NotBlank(message = "Password cannot be empty.")
    private String password;
}