package ru.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.LoginDto;
import ru.itis.dto.TokenDto;
import ru.itis.services.SignInService;

@RestController
public class SignInRestController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/rest/signIn")
    public ResponseEntity<TokenDto> signIn(@ModelAttribute LoginDto signInData) {
        return ResponseEntity.ok(signInService.signIn(signInData));
    }
    @GetMapping("/rest/signIn")
    public ResponseEntity<TokenDto> signInGet(@ModelAttribute LoginDto signInData) {
        return ResponseEntity.ok(signInService.signIn(signInData));
    }
}