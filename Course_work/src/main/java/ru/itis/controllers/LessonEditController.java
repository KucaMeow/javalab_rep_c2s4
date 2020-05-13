package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.models.Lesson;
import ru.itis.services.LessonsService;

import javax.transaction.Transactional;

@Controller
public class LessonEditController {

    @Autowired
    LessonsService lessonsService;

    @Transactional
    @GetMapping("/edit/lesson/{id}")
    public String getEditPage(@PathVariable long id, Model model) {
        Lesson lesson = lessonsService.getLessont(id);
        if(lesson == null) {
            return "redirect:/create/list";
        }
        model.addAttribute("lesson", lesson);
        return "editLesson";
    }

    @PostMapping("create/lesson")
    public String createCourse(@RequestParam long courseId) {
        Lesson lesson = lessonsService.addLessonToCourse(courseId);
        return "redirect:/edit/lesson/" + lesson.getId();
    }
}
