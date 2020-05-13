package ru.itis.controllers;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.models.Course;
import ru.itis.services.CoursesService;

@Controller
public class CourseEditController {

    @Autowired
    CoursesService coursesService;

    @GetMapping("/edit/course/{id}")
    public String getEditPage(@PathVariable long id, Model model) {
        Course course = coursesService.getCourse(id);
        if(course == null) {
            return "redirect:/create/list";
        }
        model.addAttribute("course", course);
        return "editCourse";
    }

    @PostMapping("/edit/course/{id}")
    public String editCourse(@PathVariable long id, @RequestParam String name, @RequestParam String desc) {
        coursesService.updateCourseById(id, name, desc);
        return "redirect:/create/list";
    }

    @GetMapping("create/course")
    public String createCourse() {
        Course course = coursesService.createEmptyCourse();
        return "redirect:/edit/course/" + course.getId();
    }
}
