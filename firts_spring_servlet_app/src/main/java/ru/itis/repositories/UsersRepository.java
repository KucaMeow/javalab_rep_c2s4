package ru.itis.repositories;

import ru.itis.dto.RegisterDto;
import ru.itis.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Long, User> {
    User save(RegisterDto entity);
    void verify(String email);
    Optional<User> findByEmail(String username);
}
