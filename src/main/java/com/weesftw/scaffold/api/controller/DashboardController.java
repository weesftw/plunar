package com.weesftw.scaffold.api.controller;

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
    @GetMapping
    public ModelAndView getIndex()
    {
        return new ModelAndView("dashboard/index");
    }
}
