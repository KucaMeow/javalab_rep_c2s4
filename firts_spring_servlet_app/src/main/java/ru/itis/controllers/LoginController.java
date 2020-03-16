package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.config.AppConfig;
import ru.itis.dto.LoginDto;
import ru.itis.services.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView logIn(LoginDto user) {
        if(loginService.userIsPresent(user)) {
            AppConfig.session().setAttribute("user", user);
            return new ModelAndView("redirect:/files");
        }
        else
            return new ModelAndView("redirect:/login");
    }

}
