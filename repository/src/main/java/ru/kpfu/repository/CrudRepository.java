package ru.kpfu.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository <ID, MDL> {
    Optional<MDL> find (ID id);
    List<MDL> findAll ();
    void save (MDL entity);
    void delete (ID id);
}
