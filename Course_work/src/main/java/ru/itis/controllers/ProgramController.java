package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.dto.ProgramResult;
import ru.itis.services.CodeExecuteService;

@Controller
public class ProgramController {
    @Autowired
    CodeExecuteService codeExecuteService;

    @PostMapping("/execute")
    public ProgramResult handleProgram(@RequestParam("code") String code) {
        return codeExecuteService.executeProgram(code);
    }
}
