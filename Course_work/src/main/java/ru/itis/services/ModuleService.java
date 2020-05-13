package ru.itis.services;


import ru.itis.models.CourseModule;

public interface ModuleService {
    CourseModule getCouseModule(long id);

    CourseModule addTextLessonToLesson(long lessonId);

    CourseModule addCheckboxTestToLesson(long lessonId);

    CourseModule addCodeTaskToLesson(long lessonId);

    Long updateLearningText(long id, String text);

    Long updateCheckboxTest(long id, String question, String[] answers, String correctAnswer);

    Long updateCodeTask(long id, String[] inputs, String[] outputs, String text);
}
