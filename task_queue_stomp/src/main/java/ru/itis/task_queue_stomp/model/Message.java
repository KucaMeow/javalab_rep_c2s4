package ru.itis.task_queue_stomp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    String command;
    String queueName;
    String messageId;
    Map<String, Object> body;
}
