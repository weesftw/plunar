package com.weesftw.scaffold.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO
{
    @NotBlank(message = "Username cannot be empty.")
    private String username;
    @NotBlank(message = "Password cannot be empty.")
    private String password;
}
