package ru.itis.task_queue_stomp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import ru.itis.task_queue_stomp.model.Task;
import ru.itis.task_queue_stomp.services.TaskService;

@Controller
public class StompMessagesController {

    @Autowired
    TaskService taskService;

    @MessageMapping("/newtask")
    @SendTo("/stomp/got")
    public TextMessage createNewTask(Message<String> message) {
        Task t = Task.builder().ready(false).build();
        taskService.saveTask(t);
        return new TextMessage(t.getId());
    }
}
