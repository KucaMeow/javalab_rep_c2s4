package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Course;
import ru.itis.models.Lesson;
import ru.itis.repositories.CousresRepository;
import ru.itis.repositories.LessonsRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonsService {

    @Autowired
    LessonsRepository lessonsRepository;
    @Autowired
    CousresRepository cousresRepository;

    @Override
    public Lesson getLessont(long id) {
        Optional<Lesson> lessonOptional = lessonsRepository.find(id);
        return lessonOptional.orElse(null);
    }

    @Override
    public Lesson addLessonToCourse(long courseId) {
        Course course = cousresRepository.find(courseId).get();
        Lesson lesson = Lesson.builder()
                .orderNumber(course.getLessons().size())
                .course(course)
                .courseModules(new ArrayList<>())
                .build();
        course.getLessons().add(lesson);
        lessonsRepository.save(lesson);
        return lesson;
    }
}
