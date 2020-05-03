package ru.itis.javalabhw.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.javalabhw.model.Message;
import ru.itis.javalabhw.model.Task;
import ru.itis.javalabhw.services.TaskService;

import java.io.IOException;
import java.util.*;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    TaskService taskServiceImpl;

    Map<String, List<WebSocketSession>> sessions;

    public WebSocketHandler() {
        sessions = new HashMap<>();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Message msg = objectMapper.readValue(message.getPayload(), Message.class);
        if(msg.getCommand().equals("subscribe")) {
            if(!sessions.containsKey(msg.getQueueName())) {
                sessions.put(msg.getQueueName(), new ArrayList<>());
            }
            sessions.get(msg.getQueueName()).add(session);
        } else if(msg.getCommand().equals("receive")) {
            Task task = Task.builder()
                    .id(UUID.randomUUID().toString())
                    .queueName(msg.getQueueName())
                    .ready(false)
                    .build();
            taskServiceImpl.saveTask(task);
            session.sendMessage(new TextMessage(
                    objectMapper.writeValueAsString(
                            Message.builder()
                                    .messageId(task.getId())
                                    .command("accepted")
                                    .queueName(task.getQueueName())
                                    .build()
                    )
            ));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        for(List<WebSocketSession> webSocketSessions : sessions.values()) {
            webSocketSessions.remove(session);
        }
        super.afterConnectionClosed(session, status);
    }

    public void sendTaskComplete (Task task) {
        if(!sessions.containsKey(task.getQueueName()))
            return;
        for(WebSocketSession session : sessions.get(task.getQueueName())) {
            try {
                session.sendMessage(new TextMessage(
                        objectMapper.writeValueAsString(
                                Message.builder()
                                        .queueName(task.getQueueName())
                                        .messageId(task.getId())
                                        .command("completed")
                                        .build()
                        )
                ));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
