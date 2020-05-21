package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.dto.LessonInformationDto;
import ru.itis.models.Course;
import ru.itis.models.Lesson;
import ru.itis.services.CoursesService;
import ru.itis.services.LessonsService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class ShowCourseController {

    @Autowired
    CoursesService coursesService;
    @Autowired
    LessonsService lessonsService;

    @GetMapping("/courses")
    @Transactional
    public String getCoursesListPage(Model model) {
        List<Course> courses = coursesService.getAllCourses();
        model.addAttribute("courses", courses);
        return "coursesList";
    }

    @Transactional
    @GetMapping("/learn/{id}")
    public String getCoursePage (@PathVariable long id, Model model) {
        Course course = coursesService.getCourse(id);
        model.addAttribute("course", course);
        return "coursePage";
    }

    @Transactional
    @GetMapping("/learn/lesson/{id}")
    public String getLessonPage (@PathVariable long id, Model model) {
        Lesson lesson = lessonsService.getLessont(id);
        LessonInformationDto lessonInfo = lessonsService.getLessonInfo(id);
        model.addAttribute("lesson", lesson);
        model.addAttribute("lessonInfo", lessonInfo);
        return "lessonPage";
    }
}
