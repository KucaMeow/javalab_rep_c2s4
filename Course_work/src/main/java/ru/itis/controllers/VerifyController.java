package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.EmailVerifyDto;
import ru.itis.services.EmailService;

@Controller
public class VerifyController {

    @Autowired
    EmailService emailVerificationService;

    @RequestMapping("/verify")
    public ModelAndView verifyEmail(EmailVerifyDto email) {
        emailVerificationService.verify(email.getEmail());
        return new ModelAndView("redirect:/login");
    }

}
