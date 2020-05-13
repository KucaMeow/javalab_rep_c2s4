package ru.itis.services;


import ru.itis.models.Course;

import java.util.List;

public interface CoursesService {
    List<Course> getAllCourses();

    Course getCourse(long id);

    Course createEmptyCourse();

    void updateCourseById(long id, String name, String description);
}
