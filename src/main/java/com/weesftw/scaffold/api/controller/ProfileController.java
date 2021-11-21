package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.api.config.UserDetailsImpl;
import com.weesftw.scaffold.api.dto.ProfileDTO;
import com.weesftw.scaffold.domain.model.Account;
import com.weesftw.scaffold.domain.service.AccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController
{
    private final ModelMapper modelMapper;
    private final AccountService service;

    @GetMapping
    public ModelAndView get(ProfileDTO profileDTO)
    {
        Account principal = ((UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getAccount();

        ProfileDTO dto = new ProfileDTO();
        BeanUtils.copyProperties(principal, dto);

        return new ModelAndView("profile/index")
                .addObject(dto);
    }

    @PostMapping
    public ModelAndView post(@Valid @ModelAttribute("profileDTO") ProfileDTO profileDTO, BindingResult result)
    {
        if(result.hasErrors())
            return new ModelAndView("profile/index");

        service.save(toEntity(profileDTO));

        return new ModelAndView("dashboard/index");
    }

    private Account toEntity(ProfileDTO profileDTO)
    {
        return modelMapper.map(profileDTO, Account.class);
    }
}
