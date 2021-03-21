package com.wesleysoares.scaffold.api.request;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserResponse
{
    private UserRequest userRequest;
    private OffsetDateTime dateTime;
    private Boolean status;

    public UserResponse()
    {
        this.dateTime = OffsetDateTime.now();
        this.status = true;
    }
}
