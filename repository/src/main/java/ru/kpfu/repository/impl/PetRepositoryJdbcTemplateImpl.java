package ru.kpfu.repository.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.kpfu.model.Pet;
import ru.kpfu.model.Toy;
import ru.kpfu.repository.PetRepository;
import ru.kpfu.repository.ToyRepository;

import java.sql.PreparedStatement;
import java.util.*;

public class PetRepositoryJdbcTemplateImpl implements PetRepository {

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "SELECT pet.*, toy.id AS t_id, toy.name AS t_name FROM pet LEFT JOIN toy ON pet.id = toy.pet_id WHERE pet.id = ?";
    //language=SQL
    private final static String SQL_SELECT_ALL = "SELECT pet.*, toy.id AS t_id, toy.name AS t_name FROM pet LEFT JOIN toy ON pet.id = toy.pet_id";
    //language=SQL
    private final static String SQL_INSERT= "INSERT INTO pet(name) VALUES (?)";
    //language=SQL
    private final static String SQL_UPDATE= "UPDATE pet SET name = ? WHERE id = ?";
    //language=SQL
    private final static String SQL_DELETE= "DELETE FROM pet WHERE id = ?";
    private JdbcTemplate jdbcTemplate;

    private static Map<Long, Pet> pets = new HashMap<>();

    public PetRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Pet> petRowMapper = (row, rowNum) -> {
        Long id = row.getLong("id");
        if(!pets.containsKey(id)) {
            pets.put(id, Pet.builder()
                    .id(id)
                    .name(row.getString("name"))
                    .toys(new ArrayList<>())
                    .build());
        }
        pets.get(id)
                .getToys()
                .add(
                        Toy.builder()
                                .id(row.getLong("t_id"))
                                .name(row.getString("t_name"))
                                .build());
        return pets.get(id);
    };

    @Override
    public Optional<Pet> find(Long id) {
        jdbcTemplate.query(SQL_SELECT_BY_ID, new Object[] {id}, petRowMapper);
        if(pets.containsKey(id))
            return Optional.of(pets.get(id));
        else
            return Optional.empty();
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
                    .prepareStatement(SQL_INSERT, new String[] {"id"});
            statement.setString(1, entity.getName());
            return statement;
        }, keyHolder);

        entity.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void update(Pet entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getName(), entity.getId());
    }

    @Override
    public void saveWithToys(Pet entity) {
        save(entity);
        Long pet_id = entity.getId();

        ToyRepository tr = new ToyRepositoryJdbcTemplateImpl(jdbcTemplate);

        for(Toy t : entity.getToys()) {
            t.setPet_id(pet_id);
            tr.save(t);
        }
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void deleteWithToys (Long id) {
        Optional<Pet> optionalPet = find(id);
        if(optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            //Удалили питомца
            delete(pet.getId());

            ToyRepository tr = new ToyRepositoryJdbcTemplateImpl(jdbcTemplate);
            for(Toy t : pet.getToys()) {
                //Удалили игрушку
                tr.delete(t.getId());
            }
        }
    }

    @Override
    public void updateWithToys(Pet entity) {
        deleteWithToys(entity.getId());
        saveWithToys(entity);
    }
}
