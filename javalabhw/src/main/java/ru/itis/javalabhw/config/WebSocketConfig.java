package ru.itis.javalabhw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.itis.javalabhw.handlers.AuthHandshakeHandler;
import ru.itis.javalabhw.handlers.WebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    WebSocketHandler webSocketHandler;
    @Autowired
    AuthHandshakeHandler authHandshakeHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(webSocketHandler, "/javalabqueue")
                .setHandshakeHandler(authHandshakeHandler)
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
