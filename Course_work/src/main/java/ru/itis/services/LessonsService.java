package ru.itis.services;


import ru.itis.models.Lesson;

public interface LessonsService {
    Lesson getLessont(long id);

    Lesson addLessonToCourse(long courseId);
}
