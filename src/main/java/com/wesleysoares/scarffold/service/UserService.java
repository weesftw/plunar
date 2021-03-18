package com.wesleysoares.scarffold.service;

import com.wesleysoares.scarffold.model.User;
import com.wesleysoares.scarffold.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public User findById(String id)
    {
        return userRepository.findById(id).get();
    }

    public User save(User user)
    {
        return userRepository.save(user);
    }

    public void deleteById(String id)
    {
        userRepository.deleteById(id);
    }
}
