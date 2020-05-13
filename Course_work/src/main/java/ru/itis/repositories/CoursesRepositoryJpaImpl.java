package ru.itis.repositories;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ru.itis.models.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class CoursesRepositoryJpaImpl implements CousresRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Course> find(Long id) {
        Course course = entityManager.find(Course.class, id);
        if(course != null) {
            Hibernate.initialize(course.getLessons());
        }
        return Optional.ofNullable(course);
    }


    @Transactional
    @Override
    public List<Course> findAll() {
        return entityManager.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }


    @Transactional
    @Override
    public void save(Course entity) {
        entityManager.persist(entity);
    }


    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
