package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
@Controller
public class MainController {
    @RequestMapping("/")
    public ModelAndView defaultPage() {
        return new ModelAndView("redirect:/files");
    }
}
