package com.weesftw.scaffold.util;

import com.weesftw.scaffold.domain.model.User;

public class UserManager
{
    public static User createUser()
    {
        return User.builder().name("Wesley").build();
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