package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.dto.RegisterDto;
import ru.itis.repositories.UsersRepository;

@Component
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public boolean saveUser (RegisterDto user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usersRepository.save(user);
            emailService.sendVerify(user);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }


}
