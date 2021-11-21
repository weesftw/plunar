package com.weesftw.scaffold.api.config;

import com.weesftw.scaffold.domain.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails
{
    private final Account account;

    public Account getAccount()
    {
        return account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return account.getGroup()
                .getPermissions()
                .stream()
                .map((x) ->
                    new SimpleGrantedAuthority(x.getName().toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword()
    {
        return account.getPassword();
    }

    @Override
    public String getUsername()
    {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return account.isEnable();
    }
}
