package com.wesleysoares.scaffold.util;

import com.wesleysoares.scaffold.api.request.UserRequest;
import com.wesleysoares.scaffold.domain.model.User;

public class UserManager
{
    public static User createUser()
    {
        return User.builder().name("Wesley").build();
    }

    public static UserRequest createUserRequest()
    {
        return UserRequest.builder().name("Wesley").build();
    }

    public static User createUserWithId()
    {
        return User.builder().id("1").name("Wesley").build();
    }

    public static User createUserUpdated()
    {
        return User.builder().id("1").name("Leticia").build();
    }
}