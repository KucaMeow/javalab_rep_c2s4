package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.RegisterDto;
import ru.itis.models.Role;
import ru.itis.models.State;
import ru.itis.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJpaImpl implements UsersRepository {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User save(RegisterDto entity) {
        User user = User.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(Role.ROLE_USER)
                .state(State.NOT_CONFIRMED)
                .build();
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public void verify(String email) {
        User user = findByEmail(email).get();
        user.setState(State.CONFIRMED);
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String username) {
        Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.email = '" + username + "'", User.class);
        User user = (User) q.getSingleResult();
        if(user != null)
            return Optional.of(user);
        else
            return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> find(Long id) {
        User user = entityManager.find(User.class, id);
        if(user != null)
            return Optional.of(user);
        else
            return Optional.empty();
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void save(User entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
