package ru.itis.websocketclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import ru.itis.websocketclient.interfaces.MessagesHandler;
import ru.itis.websocketclient.model.Message;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class WebsocketclientApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        SpringApplication.run(WebsocketclientApplication.class, args);

        //TECT
        JlmqConnector connector = Jlmq
                .connector()
                .port(600)
                .connect();

        JlmqProducer producer = connector
                .producer()
                .toQueue("documents_for_generate")
                .create();

        JlmqConsumer consumer = connector
                .consumer()
                .subscribe("documents_for_generate")
                .onReceive(new MessagesHandler() {
                    @Override
                    public void handleAcceptCommand(Message message) {
                        System.out.println(message.toString());
                    }

                    @Override
                    public void handleCompleteCommand(Message message) {
                        System.out.println(message.toString());
                    }
                })
                .create();

        producer.send("Hello!");
    }
}
