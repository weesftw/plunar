package com.wesleysoares.scaffold.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest
{
    @NotBlank(message = "Name cannot be empty.")
    @Size(min = 3, message = "Name cannot be < than 3 characters.")
    @Size(max = 255, message = "Name cannot be > than 255 characters.")
    private String name;
}
