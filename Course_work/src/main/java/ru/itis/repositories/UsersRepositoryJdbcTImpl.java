package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.dto.RegisterDto;
import ru.itis.models.Role;
import ru.itis.models.State;
import ru.itis.models.User;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
//@Deprecated
public class UsersRepositoryJdbcTImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM public.user WHERE id = ?";
    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM public.user WHERE email = ?";
    //language=SQL
    private static final String SQL_UPDATE_VERIFIED_BY_EMAIL = "UPDATE public.user SET state = 'CONFIRMED' WHERE email = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM public.user";
    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO public.user(email, password, state, role) VALUES (?, ?, ?, ?)";
    //language=SQL
    private final static String SQL_DELETE= "DELETE FROM public.user WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id"))
                    .email(row.getString("email"))
                    .password(row.getString("password"))
                    .state(State.valueOf(row.getString("state")))
                    .role(Role.valueOf(row.getString("role")))
                    .build();

    @Override
    public Optional<User> find(Long id) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public void save(User entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT, new String[] {"id"});
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getState().toString());
            statement.setString(4, entity.getRole().toString());
            return statement;
        }, keyHolder);

        entity.setId(keyHolder.getKey().longValue());
    }

    @Override
    public User save(RegisterDto entity) {
        User temp = User.builder()
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(Role.ROLE_USER)
                .state(State.NOT_CONFIRMED)
                .build();
        save(temp);
        return temp;
    }

    @Override
    public void verify(String email) {
        jdbcTemplate.update(SQL_UPDATE_VERIFIED_BY_EMAIL, email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, new Object[]{email}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }
}
