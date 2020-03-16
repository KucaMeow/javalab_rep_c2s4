package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.config.AppConfig;
import ru.itis.dto.LoginDto;
import ru.itis.models.FileInfo;
import ru.itis.repositories.FileRepository;

import java.io.*;
import java.util.UUID;

@Service("service")
@PropertySource("classpath:application.properties")
public class FileService {

    @Autowired
    private Environment environment;
    @Autowired
    private FileRepository fileRepository;

    private String path;

    public FileInfo saveFile (MultipartFile file) {
        path = environment.getProperty("storage.path");
        String filename = UUID.randomUUID().toString();
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
        FileInfo rawFile = FileInfo.builder()
                .size(file.getSize())
                .name(filename + "." + ext)
                .origName(file.getOriginalFilename())
                .type(file.getContentType())
                .extention(ext)
                .uploadUser(((LoginDto)AppConfig.session().getAttribute("user")).getEmail())
                .path(path + filename + "." + ext)
                .state(0)
                .build();
        if(!(new File(path).exists())) {
            (new File(path)).mkdir();
        }
        try {
            file.transferTo(new File(rawFile.getPath()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        fileRepository.save(rawFile);
        return rawFile;
    }

    public InputStream getFileIS(String fileName) {
        try {
            return new FileInputStream(fileRepository.getFile(fileName));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

}
