package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.api.dto.LoginDTO;
import com.weesftw.scaffold.domain.model.Account;
import com.weesftw.scaffold.domain.service.MemberService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class MainController
{
    private final MemberService memberService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    @GetMapping
    public ModelAndView getIndex()
    {
        return new ModelAndView("index")
                .addObject("counter", memberService.getCount());
    }

    @GetMapping("/login")
    public ModelAndView getLogin(LoginDTO loginDTO)
    {
        return new ModelAndView("login");
    }

    private Account toEntity(LoginDTO loginDTO)
    {
        return modelMapper.map(loginDTO, Account.class);
    }
}