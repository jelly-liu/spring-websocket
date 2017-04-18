package com.jelly.websocket.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by jelly on 2017/3/17.
 */
@Configuration
@EnableWebSocket
public class WebsocketConfiguration implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //for standard websocket protocol
        webSocketHandlerRegistry
                .addHandler(helloWebsocketEndPoint(), "/websck")
                .addInterceptors(new WebsocketHandshakeInterceptor())
                .setAllowedOrigins("http://", "https://", "*");

        //for the brower that not support standart websocket protocol
        //may be rolling pull or long pull
        webSocketHandlerRegistry
                .addHandler(helloWebsocketEndPoint(), "/sockjs/websck")
                .addInterceptors(new WebsocketHandshakeInterceptor())
                .setAllowedOrigins("http://", "https://", "*").withSockJS();
    }

    @Bean
    public HelloWebsocketEndPoint helloWebsocketEndPoint(){
        return new HelloWebsocketEndPoint();
    }
}
