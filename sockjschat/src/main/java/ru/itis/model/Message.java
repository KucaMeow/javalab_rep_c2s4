package ru.itis.model;

import lombok.Data;

@Data
public class Message {
    private int roomId;
    private String from;
    private String text;
}
