package com.weesftw.scaffold.api.controller;

import com.weesftw.scaffold.api.dto.AccountDTO;
import com.weesftw.scaffold.domain.exception.AccountNotFoundException;
import com.weesftw.scaffold.domain.model.Account;
import com.weesftw.scaffold.domain.service.AccountService;
import com.weesftw.scaffold.domain.service.GroupService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final GroupService groupService;
    private final AccountService accountService;

    @GetMapping
    public ModelAndView getIndex(AccountDTO accountDTO)
    {
        return new ModelAndView("account/index")
                .addObject("groups", groupService.findAll())
                .addObject("newAccount", true);
    }

    @GetMapping("{id}")
    public ModelAndView getMember(@PathVariable("id") String id, AccountDTO accountDTO) throws AccountNotFoundException
    {
        Account account = accountService.findById(UUID.fromString(id));

        if(account != null)
        {
            BeanUtils.copyProperties(account, accountDTO);
            accountDTO.setIsEnable(account.isEnable());

            return new ModelAndView("account/index")
                    .addObject(accountDTO)
                    .addObject("groups", groupService.findAll());
        }

        return new ModelAndView("redirect:/dashboard");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id) throws AccountNotFoundException
    {
        Account member = accountService.findById(UUID.fromString(id));

        if(member != null)
            accountService.delete(member);

        return new ModelAndView("redirect:/dashboard");
    }

    @PostMapping
    public ModelAndView postAccount(@Valid @ModelAttribute("accountDTO") AccountDTO accountDTO,
                                   BindingResult result)
    {
        if(result.hasErrors())
            return new ModelAndView("account/index")
                    .addObject("groups", groupService.findAll())
                    .addObject("newAccount", true);

        accountService.save(toEntity(accountDTO));

        return new ModelAndView("redirect:/dashboard");
    }

    @PostMapping("{id}")
    public ModelAndView postAccountById(@PathVariable("id") String id,
                                       @Valid @ModelAttribute("accountDTO") AccountDTO accountDTO,
                                       BindingResult result)
    {
        Account account = accountService.findById(UUID.fromString(id));

        if(result.getModel().get("password") != null)
        {
            if(result.hasErrors())
            {
                return new ModelAndView("account/index");
            }
        }

        if(account != null)
        {
            accountDTO.setUuid(account.getUuid());
            accountDTO.setPassword(account.getPassword());
        }

        accountService.save(toEntity(accountDTO));

        return new ModelAndView("redirect:/dashboard");
    }

    private Account toEntity(AccountDTO accountDTO)
    {
        return modelMapper.map(accountDTO, Account.class);
    }
}
