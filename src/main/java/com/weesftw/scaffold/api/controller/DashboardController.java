package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.domain.service.AccountService;
import com.weesftw.scaffold.domain.service.MemberService;
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
    private final MemberService memberService;
    private final AccountService accountService;

    @GetMapping
    public ModelAndView getIndex()
    {
        return new ModelAndView("dashboard/index")
                .addObject("members", memberService.findAll())
                .addObject("users", accountService.findAll());
    }
}
