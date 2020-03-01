package ru.kpfu.repository.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.kpfu.model.Pet;
import ru.kpfu.repository.PetRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class PetRepositoryJdbcTemplateImpl implements PetRepository {

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM pet WHERE id = ?";
    //language=SQL
    private final static String SQL_SELECT_ALL = "SELECT * FROM pet";
    //language=SQL
    private final static String SQL_INSERT= "INSERT INTO pet(name) VALUES (?)";
    //language=SQL
    private final static String SQL_DELETE= "DELETE FROM pet WHERE id = ?";
    private JdbcTemplate jdbcTemplate;

    public PetRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Pet> petRowMapper = (row, rowNum) ->
            Pet.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .build();

    @Override
    public Optional<Pet> find(Long id) {
        try {
            Pet pet = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[] {id}, petRowMapper);
            return Optional.ofNullable(pet);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Pet> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, petRowMapper);
    }

    @Override
    public void save(Pet entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getName());
            return statement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }
}
