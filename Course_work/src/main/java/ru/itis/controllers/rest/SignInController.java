package ru.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.LoginDto;
import ru.itis.dto.TokenDto;
import ru.itis.services.SignInService;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody LoginDto signInData) {
        return ResponseEntity.ok(signInService.signIn(signInData));
    }
}