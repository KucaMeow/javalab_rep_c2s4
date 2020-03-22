package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.dto.LoginDto;
import ru.itis.models.State;
import ru.itis.repositories.UsersRepository;

@Service
public class LoginService {

    @Autowired
    private UsersRepository usersRepository;

    public boolean userIsPresent (LoginDto user) {
        return usersRepository.findByEmail(user.getEmail()).isPresent();
    }

    public boolean userIsVerified (LoginDto user) {
        return usersRepository.findByEmail(user.getEmail()).get().getState().equals(State.CONFIRMED);
    }
}
