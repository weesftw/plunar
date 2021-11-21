package com.weesftw.scaffold.domain.service;

import com.weesftw.scaffold.domain.exception.GroupNotFoundException;
import com.weesftw.scaffold.domain.model.Group;
import com.weesftw.scaffold.domain.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupService
{
    private final GroupRepository repository;

    public List<Group> findAll()
    {
        return repository.findAll();
    }

    public Group findById(Integer id)
    {
        return repository
                .findById(id)
                .orElseThrow(() -> new GroupNotFoundException("ID: " + id + " not found."));
    }

    @Transactional
    public Group save(Group group)
    {
        return repository.save(group);
    }

    @Transactional
    public void delete(Group group)
    {
        repository.delete(group);
    }
}
