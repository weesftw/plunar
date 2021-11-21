package com.weesftw.scaffold.domain.service;

import com.weesftw.scaffold.domain.exception.PermissionNotFoundException;
import com.weesftw.scaffold.domain.model.Permission;
import com.weesftw.scaffold.domain.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PermissionService
{
    private final PermissionRepository repository;

    public List<Permission> findAll()
    {
        return repository.findAll();
    }

    public Permission findById(Integer id)
    {
        return repository
                .findById(id)
                .orElseThrow(() -> new PermissionNotFoundException("ID: " + id + " not found."));
    }

    @Transactional
    public Permission save(Permission permission)
    {
        return repository.save(permission);
    }

    @Transactional
    public void delete(Permission permission)
    {
        repository.delete(permission);
    }
}
