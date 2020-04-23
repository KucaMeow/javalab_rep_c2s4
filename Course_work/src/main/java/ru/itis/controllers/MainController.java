package ru.itis.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.config.security.details.UserDetailsImpl;

import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
@Controller
public class MainController {

    @RequestMapping("/")
    public String redirectToDefault() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public ModelAndView defaultPage(Authentication authentication) {
        return new ModelAndView("chat", "email", ((UserDetailsImpl) authentication.getPrincipal()).getUsername());
    }
}
