package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.api.config.UserDetailsImpl;
import com.weesftw.scaffold.api.config.UserDetailsServiceImpl;
import com.weesftw.scaffold.api.dto.ProfileDTO;
import com.weesftw.scaffold.domain.model.Account;
import com.weesftw.scaffold.domain.service.AccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

        Account account = service.findById(principal.getUuid());

        ProfileDTO dto = new ProfileDTO();
        BeanUtils.copyProperties(account, dto);

        return new ModelAndView("profile/index")
                .addObject(dto);
    }

    @PostMapping
    public ModelAndView post(@Valid @ModelAttribute("profileDTO") ProfileDTO profileDTO, BindingResult result)
    {
        if(result.hasErrors())
            return new ModelAndView("profile/index");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = ((UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getAccount();

        BeanUtils.copyProperties(profileDTO, account);

        UserDetailsImpl userDetailsService = new UserDetailsImpl(account);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        userDetailsService,
                        userDetailsService.getPassword(),
                        userDetailsService.getAuthorities())
        );

        System.out.println("account: " + userDetailsService.getPassword());

        service.save(account);

        return new ModelAndView("redirect:/dashboard");
    }

    private Account toEntity(ProfileDTO profileDTO)
    {
        return modelMapper.map(profileDTO, Account.class);
    }
}
