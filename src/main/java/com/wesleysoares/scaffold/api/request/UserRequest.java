package com.wesleysoares.scaffold.api.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRequest
{
    @NotBlank(message = "Name cannot be empty.")
    @Size(min = 3, message = "Name cannot be < than 3")
    private String name;
}
