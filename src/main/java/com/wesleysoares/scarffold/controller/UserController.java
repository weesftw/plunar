package com.wesleysoares.scarffold.controller;

import com.wesleysoares.scarffold.model.User;
import com.wesleysoares.scarffold.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> post(@RequestBody User user)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> put(@PathVariable String id, @RequestBody User user)
    {
        user.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        userService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
