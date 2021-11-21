package com.weesftw.scaffold.api.config;

import com.weesftw.scaffold.domain.model.Account;
import com.weesftw.scaffold.domain.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final AccountService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Account account = service.findByUsername(username);

        if(account != null)
        {
            return new UserDetailsImpl(account);
        }

        throw new UsernameNotFoundException("Account not found.");
    }
}
