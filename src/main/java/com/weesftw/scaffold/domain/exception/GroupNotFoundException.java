package com.weesftw.scaffold.domain.exception;

public class GroupNotFoundException extends RuntimeException
{
    public GroupNotFoundException(String message)
    {
        super(message);
    }
}
