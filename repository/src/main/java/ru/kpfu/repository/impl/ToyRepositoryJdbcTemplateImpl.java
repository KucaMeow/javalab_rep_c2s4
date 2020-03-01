package ru.kpfu.repository.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.kpfu.model.Pet;
import ru.kpfu.model.Toy;
import ru.kpfu.repository.ToyRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class ToyRepositoryJdbcTemplateImpl implements ToyRepository {

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM toy WHERE id = ?";
    //language=SQL
    private final static String SQL_SELECT_ALL = "SELECT * FROM toy";
    //language=SQL
    private final static String SQL_SELECT_ALL_BY_PET_ID = "SELECT * FROM toy WHERE pet_id = ?";
    //language=SQL
    private final static String SQL_INSERT= "INSERT INTO toy(name, pet_id) VALUES (?, ?)";
    //language=SQL
    private final static String SQL_DELETE= "DELETE FROM pet WHERE id = ?";
    private JdbcTemplate jdbcTemplate;

    public ToyRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Toy> toyRowMapper = (row, rowNum) ->
            Toy.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .build();

    @Override
    public Optional<Toy> find(Long id) {
        try {
            Toy toy = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[] {id}, toyRowMapper);
            return Optional.ofNullable(toy);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Toy> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, toyRowMapper);
    }

    @Override
    public List<Toy> findAllByPetId(Long id) {
        return jdbcTemplate.query(SQL_SELECT_ALL, new Object[] {id}, toyRowMapper);
    }

    @Override
    public void save(Toy entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getPetId());
            return statement;
        }, keyHolder);

        entity.setId((Long)keyHolder.getKey());

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }
}
