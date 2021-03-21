package com.wesleysoares.scaffold.util;

import com.wesleysoares.scaffold.domain.model.User;

public class UserManager
{
    public static User createUser()
    {
        return new User().builder().name("Wesley").build();
    }

    public static User createUserWithId()
    {
        return new User().builder().id("1").name("Wesley").build();
    }

    public static User createUserForUpdate()
    {
        return new User().builder().id("1").name("Wesley Silva").build();
    }
}
