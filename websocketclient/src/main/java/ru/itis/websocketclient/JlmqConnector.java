package ru.itis.websocketclient;

import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;
import java.util.concurrent.ExecutionException;

public class JlmqConnector {

    private int port;
    WebSocketSession socketSession;

    public JlmqConnector connect() {
        try {
            socketSession = (new StandardWebSocketClient()).doHandshake(
                                Jlmq.jlmqWebSocketHandler,
                                new WebSocketHttpHeaders(),
                                URI.create("ws://localhost:" + port + "/javalabqueue/websocket")
                            ).get();
        } catch (InterruptedException e) {
            throw new IllegalArgumentException(e);
        } catch (ExecutionException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }
    public JlmqConnector port(int port) {
        this.port = port;
        return this;
    }

    public Jlmq.JlmqProducerBuilder producer() {
        return new Jlmq.JlmqProducerBuilder(socketSession);
    }

    public Jlmq.JlmqConsumerBuilder consumer() {
        return new Jlmq.JlmqConsumerBuilder(socketSession);
    }
}
