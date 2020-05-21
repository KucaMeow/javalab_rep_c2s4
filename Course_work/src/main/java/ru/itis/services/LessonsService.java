package ru.itis.services;


import ru.itis.dto.LessonInformationDto;
import ru.itis.models.Lesson;

public interface LessonsService {
    Lesson getLessont(long id);

    Lesson addLessonToCourse(long courseId);

    LessonInformationDto getLessonInfo(long id);
}
