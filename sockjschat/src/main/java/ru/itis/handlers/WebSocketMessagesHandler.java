package ru.itis.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.sockjs.transport.session.WebSocketServerSockJsSession;
import ru.itis.model.Message;
import ru.itis.model.User;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<Integer, Map<String, WebSocketSession>> sessions = new HashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private User user;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String messageText = (String) message.getPayload();
        Message messageFromJson = objectMapper.readValue(messageText, Message.class);

        int roomId = messageFromJson.getRoomId();
        if(!sessions.containsKey(roomId)) {
            sessions.put(roomId, new HashMap<>());
        }

        if (!sessions.get(roomId).containsKey(messageFromJson.getFrom())) {
            sessions.get(roomId).put(messageFromJson.getFrom(), session);
        }

        for (WebSocketSession currentSession : sessions.get(roomId).values()) {
            currentSession.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        if(session.getClass() != WebSocketServerSockJsSession.class) {
            if(user.getUsername() == null || user.getUsername().isEmpty()) session.close();
        }
    }
}
