package ru.itis.services;

import ru.itis.dto.LoginDto;
import ru.itis.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(LoginDto login);
}
