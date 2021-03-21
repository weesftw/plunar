package com.wesleysoares.scaffold.domain.service;

import com.wesleysoares.scaffold.domain.exception.UserNotFoundException;
import com.wesleysoares.scaffold.domain.model.User;
import com.wesleysoares.scaffold.domain.repository.UserRepository;
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
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id + " id not found."));
    }

    @Transactional
    public User save(User user)
    {
        return userRepository.save(user);
    }

    public void deleteById(String id)
    {
        userRepository.deleteById(id);
    }
}
