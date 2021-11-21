package com.weesftw.scaffold.domain.exception;

public class PermissionNotFoundException extends RuntimeException
{
    public PermissionNotFoundException(String message)
    {
        super(message);
    }
}
