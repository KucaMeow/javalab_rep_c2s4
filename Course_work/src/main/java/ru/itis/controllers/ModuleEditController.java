package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.models.CourseModule;
import ru.itis.models.ModuleType;
import ru.itis.services.ModuleService;

@Controller
public class ModuleEditController {

    @Autowired
    ModuleService moduleService;

    @GetMapping("/edit/module/{moduleType}/{id}")
    public String editModule(@PathVariable String moduleType, @PathVariable long id, Model model) {
        CourseModule courseModule = moduleService.getCouseModule(id);
        if(courseModule == null) {
            return "redirect:/create/list";
        }
        switch (moduleType) {
            case "CodeTask":
                model.addAttribute("codeTask", courseModule.getCodeTask());
                return "editCodeTask";
            case "LearningText":
                model.addAttribute("learningText", courseModule.getLearningText());
                return "editLearningText";
            case "CheckboxTest":
                model.addAttribute("checkboxTest", courseModule.getCheckboxTest());
                return "editCheckboxTest";
        }
        return "redirect:/create/list";
    }

    @PostMapping("/create/text-lesson")
    public String createTextLesson(@RequestParam long lessonId) {
        CourseModule courseModule = moduleService.addTextLessonToLesson(lessonId);
        return "redirect:/edit/module/" + ModuleType.LearningText.name() + "/" + courseModule.getId();
    }
    @PostMapping("/create/checkbox-test")
    public String createCheckboxTest(@RequestParam long lessonId) {
        CourseModule courseModule = moduleService.addCheckboxTestToLesson(lessonId);
        return "redirect:/edit/module/" + ModuleType.CheckboxTest.name() + "/" + courseModule.getId();
    }
    @PostMapping("/create/code-task")
    public String createCodeTask(@RequestParam long lessonId) {
        CourseModule courseModule = moduleService.addCodeTaskToLesson(lessonId);
        return "redirect:/edit/module/" + ModuleType.CodeTask.name() + "/" + courseModule.getId();
    }
    @PostMapping("/edit/learning-text/{id}")
    public String updateLearningText(@PathVariable long id, @RequestParam String text) {
        return "redirect:/edit/lesson/" + moduleService.updateLearningText(id, text);
    }
    @PostMapping("/edit/checkbox-test/{id}")
    public String updateCheckboxTest(@PathVariable long id, @RequestParam String question, @RequestParam String[] answers, @RequestParam String correctAnswer) {
        return "redirect:/edit/lesson/" + moduleService.updateCheckboxTest(id, question, answers, correctAnswer);
    }
    @PostMapping("/edit/code-task/{id}")
    public String updateCodeTask(@PathVariable long id, @RequestParam String text, @RequestParam String[] inputs, @RequestParam String[] outputs) {
        return "redirect:/edit/lesson/" + moduleService.updateCodeTask(id, inputs, outputs, text);
    }
}
