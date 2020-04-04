package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.ErrorsService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @Autowired
    ErrorsService errorsService;

    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("errorPage");
        errorPage.addObject("errorMsg", errorsService.getErrorMessage(getErrorCode(httpRequest)));
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
