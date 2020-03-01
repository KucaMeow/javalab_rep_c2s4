package ru.kpfu.repository;

import ru.kpfu.model.Toy;

import java.util.List;

public interface ToyRepository extends CrudRepository<Long, Toy> {
    List<Toy> findAllByPetId(Long id);
}
