package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class FileInfo {
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

