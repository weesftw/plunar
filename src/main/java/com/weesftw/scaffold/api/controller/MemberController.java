package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.api.dto.MemberDTO;
import com.weesftw.scaffold.domain.exception.MemberNotFoundException;
import com.weesftw.scaffold.domain.model.Member;
import com.weesftw.scaffold.domain.service.MemberService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController
{
    private final ModelMapper modelMapper;
    private final MemberService memberService;

    @GetMapping
    public ModelAndView getIndex(MemberDTO memberDTO)
    {
        return new ModelAndView("member/index");
    }

    @GetMapping("{id}")
    public ModelAndView getMember(@PathVariable("id") Integer id, MemberDTO memberDTO) throws MemberNotFoundException
    {
        Member member = memberService.findById(id);

        if(member != null)
        {
            BeanUtils.copyProperties(member, memberDTO);
            return new ModelAndView("member/index")
                    .addObject(memberDTO);
        }

        return new ModelAndView("redirect:/dashboard");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id) throws MemberNotFoundException
    {
        Member member = memberService.findById(id);

        if(member != null)
            memberService.delete(member);

        return new ModelAndView("redirect:/dashboard");
    }

    @PostMapping()
    public ModelAndView postMember(@Valid @ModelAttribute("memberDTO") MemberDTO memberDTO,
                                   BindingResult result)
    {
        if(result.hasErrors())
            return new ModelAndView("member/index");

        memberService.save(toEntity(memberDTO));

        return new ModelAndView("redirect:/dashboard");
    }

    @PostMapping("{id}")
    public ModelAndView postMemberById(@PathVariable("id") Integer id,
                                   @Valid @ModelAttribute("memberDTO") MemberDTO memberDTO,
                                   BindingResult result)
    {
        if(result.hasErrors())
            return new ModelAndView("member/index");

        if(id != null)
            memberDTO.setId(id);

        memberService.save(toEntity(memberDTO));

        return new ModelAndView("redirect:/dashboard");
    }

    private Member toEntity(MemberDTO memberDTO)
    {
        return modelMapper.map(memberDTO, Member.class);
    }
}
