package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SanboxController {

    @RequestMapping("/sandbox")
    public ModelAndView getPage() {
        return new ModelAndView("sandbox");
    }
}
