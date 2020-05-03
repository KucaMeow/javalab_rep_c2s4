package ru.itis.javalabhw.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.javalabhw.model.Task;
import ru.itis.javalabhw.services.TaskService;

@Component
public class ReceiveTaskHandler extends TextWebSocketHandler {

    @Autowired
    TaskService taskServiceImpl;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Task task = Task.builder().ready(false).build();
        taskServiceImpl.saveTask(task);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(task)));
    }
}
