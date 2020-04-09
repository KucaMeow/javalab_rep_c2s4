package ru.itis.services;

import ru.itis.dto.RegisterDto;

public interface RegisterService {
    boolean saveUser(RegisterDto registerDto);
}
