package com.weesftw.scaffold.api.handler;

import com.weesftw.scaffold.domain.exception.AccountNotFoundException;
import com.weesftw.scaffold.domain.exception.MemberNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler
{
    @ResponseBody
    @ExceptionHandler(MemberNotFoundException.class)
    public Object handleMemberNotFoundException()
    {
        return new ModelAndView("redirect:/dashboard");
    }

    @ResponseBody
    @ExceptionHandler(AccountNotFoundException.class)
    public Object handleAccountNotFoundException()
    {
        return new ModelAndView("redirect:/dashboard");
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public Object handleIllegalArgumentException()
    {
        return new ModelAndView("redirect:/dashboard");
    }
}
