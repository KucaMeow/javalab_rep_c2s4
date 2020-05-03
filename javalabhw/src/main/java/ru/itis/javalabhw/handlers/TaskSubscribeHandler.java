package ru.itis.javalabhw.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.javalabhw.model.Task;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class TaskSubscribeHandler extends TextWebSocketHandler {

    @Autowired
    ObjectMapper objectMapper;

    Set<WebSocketSession> sessions;

    public TaskSubscribeHandler() {
        sessions = new HashSet<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        super.afterConnectionClosed(session, status);
    }

    public void sendTaskComplete (Task task) {
        for(WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(task)));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

}
