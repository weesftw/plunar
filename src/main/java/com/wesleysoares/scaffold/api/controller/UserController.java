package com.wesleysoares.scaffold.api.controller;

import com.wesleysoares.scaffold.api.request.UserRequest;
import com.wesleysoares.scaffold.domain.model.User;
import com.wesleysoares.scaffold.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class UserController
{
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ModelAndView get()
    {
        return new ModelAndView("index")
                .addObject("createUser", new UserRequest())
                .addObject("user", new User())
                .addObject("users", userService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getUser(@PathVariable String id)
    {
        User user = userService.findById(id);

        System.out.println(user);

        return user;
    }

    @PostMapping("/")
    public ModelAndView post(@Valid UserRequest userRequest, BindingResult errors)
    {
        if(!errors.hasErrors())
            userService.save(toEntity(userRequest));

        return new ModelAndView("redirect:/");
    }

    @PostMapping("/update/{id}")
    public ModelAndView put(@PathVariable String id, @Valid User user, BindingResult errors)
    {
        if(user != null)
            userService.save(user);

        return new ModelAndView("redirect:/");
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(User user)
    {
        userService.delete(user);

        return new ModelAndView("redirect:/");
    }

    private User toEntity(UserRequest userRequest)
    {
        return modelMapper.map(userRequest, User.class);
    }
}