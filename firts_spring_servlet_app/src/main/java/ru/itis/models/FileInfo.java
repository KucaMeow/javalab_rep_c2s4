package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class FileInfo {

//    private Long id;
//    // название файла в хранилище
//    private String storageFileName;
//    // название файла оригинальное
//    private String originalFileName;
//    // размер файла
//    private Long size;
//    // тип файла (MIME)
//    private String type;
//    // по какому URL можно получить файл
//    private String url;

    private Long id;
    private String name;
    private String origName;
    private long size;
    private String uploadUser;
    private String path;
    private int state;
    private String type;
    private String extention;
}

