package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.domain.repository.MemberRepository;
import com.weesftw.scaffold.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController
{
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    @GetMapping
    public ModelAndView getIndex()
    {
        return new ModelAndView("dashboard/index")
                .addObject("members", memberRepository.findAll())
                .addObject("users", userRepository.findAll());
    }
}
