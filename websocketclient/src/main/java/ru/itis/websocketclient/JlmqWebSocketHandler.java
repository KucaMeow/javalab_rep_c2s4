package ru.itis.websocketclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.itis.websocketclient.interfaces.MessagesHandler;
import ru.itis.websocketclient.model.Message;

import java.util.HashMap;
import java.util.Map;

public class JlmqWebSocketHandler implements WebSocketHandler {

     static Map<String, MessagesHandler> messagesHandlerMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("Connection established... Ok");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String messageJson = (String) webSocketMessage.getPayload();
        Message message = (new ObjectMapper()).readValue(messageJson, Message.class);
        MessagesHandler messagesHandler = messagesHandlerMap.get(message.getQueueName());
        if(messagesHandler == null)
            return;
        if(message.getCommand().equals("accepted")) {
            messagesHandler.handleAcceptCommand(message);
        }
        else if(message.getCommand().equals("completed")) {
            messagesHandler.handleCompleteCommand(message);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("Connection closed; " + closeStatus.getReason());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
