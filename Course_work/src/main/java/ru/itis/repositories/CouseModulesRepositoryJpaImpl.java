package ru.itis.repositories;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ru.itis.models.CourseModule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class CouseModulesRepositoryJpaImpl implements CourseModulesRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<CourseModule> find(Long id) {
        CourseModule courseModule = entityManager.find(CourseModule.class, id);
        if(courseModule != null) {
            Hibernate.initialize(courseModule.getLesson());
            Hibernate.initialize(courseModule.getCheckboxTest());
            Hibernate.initialize(courseModule.getCodeTask());
            Hibernate.initialize(courseModule.getLearningText());
        }
        return Optional.ofNullable(courseModule);
    }

    @Transactional
    @Override
    public List<CourseModule> findAll() {
        return entityManager.createQuery("SELECT c FROM CourseModule c", CourseModule.class).getResultList();
    }

    @Transactional
    @Override
    public void save(CourseModule entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
