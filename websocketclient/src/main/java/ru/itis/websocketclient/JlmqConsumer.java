package ru.itis.websocketclient;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.itis.websocketclient.interfaces.MessagesHandler;

import java.io.IOException;

public class JlmqConsumer {
    JlmqConsumer(String subscription, MessagesHandler messagesHandler, WebSocketSession socketSession) {
        if(JlmqWebSocketHandler.messagesHandlerMap.containsKey(subscription))
            throw new IllegalArgumentException("This subscription is busy already");
        try {
            socketSession.sendMessage(new TextMessage("{\"command\":\"subscribe\",\"queueName\":\"" + subscription + "\"}"));
            JlmqWebSocketHandler.messagesHandlerMap.put(subscription, messagesHandler);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
