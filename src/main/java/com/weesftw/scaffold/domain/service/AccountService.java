package com.weesftw.scaffold.domain.service;

import com.weesftw.scaffold.domain.exception.AccountNotFoundException;
import com.weesftw.scaffold.domain.model.Account;
import com.weesftw.scaffold.domain.model.Group;
import com.weesftw.scaffold.domain.model.Permission;
import com.weesftw.scaffold.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService
{
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository repository;

    public List<Account> findAll()
    {
        return repository.findAll();
    }

    public Account findById(UUID id)
    {
        return repository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException("ID: " + id + " not found."));
    }

    @Transactional
    public Account findByUsername(String username)
    {
        Account account = repository.findByUsername(username);
        account.getGroup()
                .getPermissions().size();

        return account;
    }

    @Transactional
    public Account save(Account account)
    {
        UUID id = account.getUuid();
        String password = account.getPassword();

        if(id == null)
        {
            account.setEnable(true);
            account.setUuid(UUID.randomUUID());
        }

        if(password != null)
            account.setPassword(passwordEncoder.encode(account.getPassword()));

        return repository.save(account);
    }

    @Transactional
    public void delete(Account account)
    {
        repository.delete(account);
    }
}
