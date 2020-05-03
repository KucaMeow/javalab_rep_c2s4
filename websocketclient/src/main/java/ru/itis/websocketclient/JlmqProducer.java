package ru.itis.websocketclient;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class JlmqProducer {
    private String queueName;
    private WebSocketSession socketSession;

    JlmqProducer(String queueName, WebSocketSession socketSession) {
        this.queueName = queueName;
        this.socketSession = socketSession;
    }

    public void send(String message) {
        try {
            socketSession.sendMessage(
                    new TextMessage(
                            "{" +
                                    "\"command\":\"receive\"," +
                                    "\"queueName\":\"" + queueName + "\"," +
                                    "\"body\":{" +
                                        "\"text\":\"" + message + "" +
                                        "\"}" +
                                    "}"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
