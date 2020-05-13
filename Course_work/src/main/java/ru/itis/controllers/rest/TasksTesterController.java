package ru.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.ProgramResult;
import ru.itis.dto.ResultDto;
import ru.itis.services.CodeExecuteService;

@RestController
public class TasksTesterController {

    @Autowired
    CodeExecuteService codeExecuteService;

    @PostMapping("/rest/execute")
    public ProgramResult handleProgram(@RequestParam("code") String code) {
        return codeExecuteService.executeProgram(code);
    }

    @PostMapping("/rest/code-test/{id}")
    public ResultDto testCodeTest(@PathVariable long id, @RequestParam String code) {
        return codeExecuteService.test(id, code);
    }

    @PostMapping("/rest/checkbox-test/{id}")
    public ResultDto checkCheckboxAnswer(@PathVariable long id, @RequestParam String answer) {
        return codeExecuteService.checkAnswerForCheckbox(id, answer);
    }
}
