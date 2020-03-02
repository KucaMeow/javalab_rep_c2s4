package ru.kpfu.repository;

import ru.kpfu.model.Pet;

public interface PetRepository extends CrudRepository<Long, Pet> {
    //Отделил метод сохранения и удаления со всеми игрушками
    void saveWithToys(Pet entity);
    void deleteWithToys(Long id);
    void updateWithToys(Pet entity);
}
