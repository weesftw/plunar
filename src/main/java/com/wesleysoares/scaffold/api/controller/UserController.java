package com.wesleysoares.scaffold.api.controller;

import com.wesleysoares.scaffold.api.request.UserRequest;
import com.wesleysoares.scaffold.domain.model.User;
import com.wesleysoares.scaffold.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;
import java.util.stream.Collectors;

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

        return user;
    }

    @PostMapping("/")
    public ModelAndView post(@Valid UserRequest userRequest, BindingResult errors, RedirectAttributes redirectAttributes)
    {
        if(!errors.hasErrors())
            userService.save(toEntity(userRequest));
        else
            redirectAttributes.addFlashAttribute("exception", errors.getAllErrors().stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.toList()));

        return new ModelAndView("redirect:/", HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ModelAndView put(@PathVariable String id, @Valid User user, BindingResult errors, RedirectAttributes redirectAttributes)
    {
        if(user != null && !errors.hasErrors())
            userService.save(user);
        else
            redirectAttributes.addFlashAttribute("exception", errors.getAllErrors().stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.toList()));

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