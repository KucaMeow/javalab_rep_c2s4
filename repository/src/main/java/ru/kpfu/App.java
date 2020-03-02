package ru.kpfu;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.kpfu.model.Pet;
import ru.kpfu.model.Toy;
import ru.kpfu.repository.PetRepository;
import ru.kpfu.repository.impl.PetRepositoryJdbcTemplateImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/PetNToys";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);


        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

//        Toy t1 = Toy.builder().name("bone").build();
//        Toy t2 = Toy.builder().name("ball").build();
//        Toy t3 = Toy.builder().name("red ball").build();
//        List<Toy> toys = new ArrayList<>();
//        toys.add(t1);
//        toys.add(t2);
//        toys.add(t3);
//        Pet newPet = Pet.builder().name("Dog").toys(toys).build();

        PetRepository pr = new PetRepositoryJdbcTemplateImpl(jdbcTemplate);
//        pr.saveWithToys(newPet);
        Optional<Pet> petOpt = pr.find(5L);
        System.out.println(petOpt.get());

    }
}
