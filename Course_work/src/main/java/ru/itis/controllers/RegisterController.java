package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.RegisterDto;
import ru.itis.services.RegisterService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/register")
    public ModelAndView getRegisterPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid RegisterDto user, BindingResult bindingResult) {
        if(bindingResult.getAllErrors().isEmpty() && registerService.saveUser(user)) {
            return new ModelAndView("email");
        }
        return new ModelAndView("register", "registerDto", user);
    }
}
