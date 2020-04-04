package ru.itis.services;

import org.springframework.stereotype.Service;

@Service
public class ErrorsService {
    public String getErrorMessage(int httpErrorCode) {
        String errorMsg;

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
                errorMsg = "Http Error Code: 403. Доступ запрещен";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Ошибка на сервере";
                break;
            }
            default:
                errorMsg = "Http Error Code: " + httpErrorCode + ". Ошибка";
        }
        return errorMsg;
    }
}
