package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.api.dto.UserDTO;
import com.weesftw.scaffold.domain.model.User;
import com.weesftw.scaffold.domain.repository.MemberRepository;
import com.weesftw.scaffold.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController
{
    private final ModelMapper modelMapper;
    private final UserRepository repository;

    @GetMapping
    public ModelAndView getIndex(UserDTO userDTO)
    {
        return new ModelAndView("user/index");
    }

    @PostMapping
    public ModelAndView postMember(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result)
    {
        if(result.hasErrors())
            return new ModelAndView("user/index");

        repository.save(toEntity(userDTO));
        return new ModelAndView("dashboard/index");
    }

    private User toEntity(UserDTO userDTO)
    {
        return modelMapper.map(userDTO, User.class);
    }
}
