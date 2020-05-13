package ru.itis.repositories;

import org.springframework.stereotype.Repository;
import ru.itis.models.LearningText;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class LearningTextesRepositoryJpaImpl implements LearningTextesRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<LearningText> find(Long id) {
        return Optional.ofNullable(entityManager.find(LearningText.class, id));
    }

    @Transactional
    @Override
    public List<LearningText> findAll() {
        return entityManager.createQuery("SELECT l FROM LearningText l", LearningText.class).getResultList();
    }

    @Transactional
    @Override
    public void save(LearningText entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
