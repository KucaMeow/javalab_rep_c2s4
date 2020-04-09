package ru.itis.services;

import ru.itis.dto.RegisterDto;

public interface EmailService {
    void sendVerify(RegisterDto user);
    void verify(String email);
}
