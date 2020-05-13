package ru.itis.repositories;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ru.itis.models.CodeTask;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class CodeTasksRepositoryJpaImpl implements CodeTasksRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<CodeTask> find(Long id) {
        CodeTask codeTask = entityManager.find(CodeTask.class,id);
        if(codeTask != null) {
            Hibernate.initialize(codeTask.getTests());
        }
        return Optional.ofNullable(codeTask);
    }

    @Transactional
    @Override
    public List<CodeTask> findAll() {
        return entityManager.createQuery("SELECT ct FROM CodeTask ct", CodeTask.class).getResultList();
    }

    @Transactional
    @Override
    public void save(CodeTask entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
