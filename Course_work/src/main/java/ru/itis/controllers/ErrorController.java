package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("errorPage");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Неправильный запрос";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Не авторизован";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Страница не найдена";
                break;
            }
            case 403: {
                errorMsg = "Http Error Code: 500. Доступ запрещен";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Ошибка на сервере";
                break;
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
