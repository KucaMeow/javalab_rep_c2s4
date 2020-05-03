package ru.itis.websocketclient;

import org.springframework.web.socket.WebSocketSession;
import ru.itis.websocketclient.interfaces.MessagesHandler;

import java.util.ArrayList;
import java.util.List;

public class Jlmq {

    private static JlmqConnector jlmqConnector;
    static JlmqWebSocketHandler jlmqWebSocketHandler = new JlmqWebSocketHandler();

    public static JlmqConnector connector(){
        if(jlmqConnector == null) {
            jlmqConnector = new JlmqConnector();
        }
        return jlmqConnector;
    }

    static class JlmqProducerBuilder {
        private String queueName;
        private WebSocketSession socketSession;

        JlmqProducerBuilder(WebSocketSession socketSession) {
            this.socketSession = socketSession;
        }

        public JlmqProducerBuilder toQueue(String queueName) {
            this.queueName = queueName;
            return this;
        }

        public JlmqProducer create() {
            return new JlmqProducer(queueName, socketSession);
        }
    }

    static class JlmqConsumerBuilder {
        private String sub;
        private WebSocketSession sessionSocket;
        private MessagesHandler messagesHandler;

        JlmqConsumerBuilder(WebSocketSession sessionSocket) {
            this.sessionSocket = sessionSocket;
        }

        public JlmqConsumerBuilder subscribe(String queueName) {
            sub = queueName;
            return this;
        }

        public JlmqConsumerBuilder onReceive(MessagesHandler messagesHandler) {
            this.messagesHandler = messagesHandler;
            return this;
        }

        public JlmqConsumer create() {
            return new JlmqConsumer(sub, messagesHandler, sessionSocket);
        }
    }
}
