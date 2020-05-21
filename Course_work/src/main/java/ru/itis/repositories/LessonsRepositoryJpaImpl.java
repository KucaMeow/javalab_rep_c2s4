package ru.itis.repositories;

import org.hibernate.Hibernate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.dto.LessonInformationDto;
import ru.itis.models.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class LessonsRepositoryJpaImpl implements LessonsRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    @Override
    public Optional<Lesson> find(Long id) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        if(lesson != null) {
            Hibernate.initialize(lesson.getCourse());
            Hibernate.initialize(lesson.getCourseModules());
        }
        return Optional.ofNullable(lesson);
    }

    @Transactional
    @Override
    public List<Lesson> findAll() {
        return entityManager.createQuery("SELECT m FROM Lesson m", Lesson.class).getResultList();
    }

    @Transactional
    @Override
    public void save(Lesson entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }

    @Override
    public Optional<LessonInformationDto> getLessonInformationDtoFromLesson(long id) {
        return Optional.ofNullable(entityManager
                .createQuery("select new ru.itis.dto.LessonInformationDto(course.author, l.courseModules.size) " +
                        "from Lesson l left join l.course as course where l.id = " + id, LessonInformationDto.class)
                .getSingleResult());
    }
}
