package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.api.dto.UserDTO;
import com.weesftw.scaffold.domain.model.User;
import com.weesftw.scaffold.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class MainController
{
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ModelAndView getIndex()
    {
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView getLogin(UserDTO userDTO)
    {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result)
    {
        if(result.hasErrors())
            return new ModelAndView("login");

        return new ModelAndView("dashboard/index");
    }

    private User toEntity(UserDTO userDTO)
    {
        return modelMapper.map(userDTO, User.class);
    }
}