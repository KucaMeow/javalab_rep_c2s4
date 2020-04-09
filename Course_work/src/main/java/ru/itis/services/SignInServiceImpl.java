package ru.itis.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.LoginDto;
import ru.itis.dto.TokenDto;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;

@Service
@PropertySource("classpath:application.properties")
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public TokenDto signIn(LoginDto login) {
        Optional<User> userOptional = usersRepository.findByEmail(login.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                String token = Jwts.builder()
                        .setSubject(user.getId().toString())
                        .claim("email", user.getEmail())
                        .claim("role", user.getRole().name())
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
                return new TokenDto(token);
            } else throw new AccessDeniedException("Wrong email/password");
        } else throw new AccessDeniedException("User not found");
    }
}
