package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private User user;

    @PostMapping("/login")
    public String methodPost (@RequestParam String login, HttpServletResponse response) {
        Cookie cookie = new Cookie("auth", "asdafawrayugf8513y6g9fg6vsdo");
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
        user.setUsername(login);
        return "redirect:/mychat";
    }

    @GetMapping("/login")
    public String methodGet () {
        return "login";
    }

}
