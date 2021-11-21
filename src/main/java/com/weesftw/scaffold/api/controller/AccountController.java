package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.api.dto.AccountDTO;
import com.weesftw.scaffold.domain.exception.AccountNotFoundException;
import com.weesftw.scaffold.domain.model.Account;
import com.weesftw.scaffold.domain.service.AccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController
{
    private final ModelMapper modelMapper;
    private final AccountService service;

    @GetMapping
    public ModelAndView getIndex(AccountDTO accountDTO)
    {
        return new ModelAndView("account/index");
    }

    @GetMapping("{id}")
    public ModelAndView getMember(@PathVariable("id") String id, AccountDTO accountDTO) throws AccountNotFoundException
    {
        Account account = service.findById(UUID.fromString(id));

        if(account != null)
        {
            BeanUtils.copyProperties(account, accountDTO);
            return new ModelAndView("account/index")
                    .addObject(accountDTO);
        }

        return new ModelAndView("redirect:/dashboard");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id) throws AccountNotFoundException
    {
        Account member = service.findById(UUID.fromString(id));

        if(member != null)
            service.delete(member);

        return new ModelAndView("redirect:/dashboard");
    }

    @PostMapping
    public ModelAndView postMember(@Valid @ModelAttribute("accountDTO") AccountDTO accountDTO,
                                   BindingResult result)
    {
        if(result.hasErrors())
            return new ModelAndView("account/index");

        service.save(toEntity(accountDTO));

        return new ModelAndView("redirect:/dashboard");
    }

    @PostMapping("{id}")
    public ModelAndView postMemberById(@PathVariable("id") String id,
                                       @Valid @ModelAttribute("accountDTO") AccountDTO accountDTO,
                                       BindingResult result)
    {
        if(result.hasErrors())
            return new ModelAndView("account/index");

        if(id != null)
            accountDTO.setUuid(UUID.fromString(id));

        System.out.println("??????????????????");

        service.save(toEntity(accountDTO));

        return new ModelAndView("redirect:/dashboard");
    }

    private Account toEntity(AccountDTO accountDTO)
    {
        return modelMapper.map(accountDTO, Account.class);
    }
}
