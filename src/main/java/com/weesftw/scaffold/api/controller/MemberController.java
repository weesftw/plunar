package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.api.dto.MemberDTO;
import com.weesftw.scaffold.domain.model.Member;
import com.weesftw.scaffold.domain.repository.MemberRepository;
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
@RequestMapping("/member")
public class MemberController
{
    private final ModelMapper modelMapper;
    private final MemberRepository repository;

    @GetMapping
    public ModelAndView getIndex(MemberDTO memberDTO)
    {
        return new ModelAndView("member/index");
    }

    @PostMapping
    public ModelAndView postMember(@Valid @ModelAttribute("memberDTO") MemberDTO memberDTO, BindingResult result)
    {
        if(result.hasErrors())
            return new ModelAndView("member/index");

        repository.save(toEntity(memberDTO));
        return new ModelAndView("dashboard/index");
    }

    private Member toEntity(MemberDTO memberDTO)
    {
        return modelMapper.map(memberDTO, Member.class);
    }
}
