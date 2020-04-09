package ru.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.ProgramResult;
import ru.itis.services.CodeExecuteService;

@RestController
public class ProgramController {

    @Autowired
    CodeExecuteService codeExecuteService;

    @PostMapping("/execute")
    @PreAuthorize("isAuthenticated()")
    public ProgramResult handleProgram(@RequestParam("code") String code) {
        return codeExecuteService.executeProgram(code);
    }
}
