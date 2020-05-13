package ru.itis.repositories;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ru.itis.models.CheckboxTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class CheckboxTestsRepositoryJpaImpl implements CheckboxTestsRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<CheckboxTest> find(Long id) {
        CheckboxTest checkboxTest = entityManager.find(CheckboxTest.class, id);
        if(checkboxTest != null) {
            Hibernate.initialize(checkboxTest.getCheckboxTestAnswers());
        }
        return Optional.ofNullable(checkboxTest);
    }

    @Transactional
    @Override
    public List<CheckboxTest> findAll() {
        return entityManager.createQuery("SELECT ct FROM CheckboxTest ct", CheckboxTest.class).getResultList();
    }

    @Transactional
    @Override
    public void save(CheckboxTest entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
