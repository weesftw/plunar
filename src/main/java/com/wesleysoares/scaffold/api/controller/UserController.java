package com.wesleysoares.scaffold.api.controller;

import com.wesleysoares.scaffold.api.request.UserRequest;
import com.wesleysoares.scaffold.api.request.UserResponse;
import com.wesleysoares.scaffold.domain.model.User;
import com.wesleysoares.scaffold.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController
{
    private final UserService userService;
    private final ModelMapper modelMapper;

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
    public ResponseEntity<User> post(@RequestBody @Valid UserRequest userRequest)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(toEntity(userRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> put(@PathVariable String id, @RequestBody @Valid UserRequest userRequest)
    {
        User user = toEntity(userRequest);
        user.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        userService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private User toEntity(UserRequest userRequest)
    {
        return modelMapper.map(userRequest, User.class);
    }
}
