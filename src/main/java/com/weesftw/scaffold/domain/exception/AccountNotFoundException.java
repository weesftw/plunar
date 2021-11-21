package com.weesftw.scaffold.domain.exception;

public class AccountNotFoundException extends RuntimeException
{
    public AccountNotFoundException(String message)
    {
        super(message);
    }
}
