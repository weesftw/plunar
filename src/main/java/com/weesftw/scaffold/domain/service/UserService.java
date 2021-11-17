package com.weesftw.scaffold.domain.service;

import com.weesftw.scaffold.domain.exception.UserNotFoundException;
import com.weesftw.scaffold.domain.model.User;
import com.weesftw.scaffold.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public User findById(String id)
    {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID: " + id + " not found."));
    }

    @Transactional
    public User save(User user)
    {
        return userRepository.save(user);
    }

    public void deleteById(String id)
    {
        userRepository.delete(findById(id));
    }

    public void delete(User user)
    {
        userRepository.delete(user);
    }
}
