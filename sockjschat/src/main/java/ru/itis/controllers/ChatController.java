package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.model.User;

import java.util.UUID;

@Controller
public class ChatController {

    @Autowired
    User user;

    @GetMapping("/mychat")
    public String getPage(Model model) {
        model.addAttribute("username", user.getUsername());
        return "chat";
    }

}
