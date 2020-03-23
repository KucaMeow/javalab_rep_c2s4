package ru.itis.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.config.AppConfig;
import ru.itis.dto.LoginDto;
import ru.itis.services.FileService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@Controller
@MultipartConfig
public class FilesController {

    @Qualifier("service")
    @Autowired
    private FileService fileService;

    @RequestMapping("/files")
    public ModelAndView getMainPage() {
//        LoginDto user = (LoginDto) AppConfig.session().getAttribute("user");
//        if(user != null) {
            return new ModelAndView("upload");
//        }
//        else
//            return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/files", method =  RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile multipartFile, Principal principal) {
        fileService.saveFile(multipartFile, principal);
        return null;
    }

    // localhost:8080/files/123809183093qsdas09df8af.jpeg

    @RequestMapping(value ="/files/{file-name:.+}" , method = RequestMethod.GET)
    public ModelAndView getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        try {
            InputStream is = fileService.getFileIS(fileName);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
        return null;
    }
}
