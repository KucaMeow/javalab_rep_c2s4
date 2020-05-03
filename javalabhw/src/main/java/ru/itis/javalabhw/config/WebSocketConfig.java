package ru.itis.javalabhw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.itis.javalabhw.handlers.AuthHandshakeHandler;
import ru.itis.javalabhw.handlers.ReceiveTaskHandler;
import ru.itis.javalabhw.handlers.TaskSubscribeHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    ReceiveTaskHandler receiveTaskHandler;
    @Autowired
    TaskSubscribeHandler taskSubscribeHandler;
    @Autowired
    AuthHandshakeHandler authHandshakeHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(taskSubscribeHandler, "/sub")
                .setHandshakeHandler(authHandshakeHandler)
                .setAllowedOrigins("*")
                .withSockJS();
        webSocketHandlerRegistry
                .addHandler(receiveTaskHandler, "/send")
                .setHandshakeHandler(authHandshakeHandler)
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
