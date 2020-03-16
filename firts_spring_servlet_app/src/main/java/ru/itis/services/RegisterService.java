package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.dto.RegisterDto;
import ru.itis.repositories.UsersRepository;

@Component
public class RegisterService {

    @Autowired
    EmailService emailService;

    @Autowired
    UsersRepository usersRepository;

    public boolean saveUser (RegisterDto user) {
        try {
            usersRepository.save(user);
            emailService.sendVerify(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
