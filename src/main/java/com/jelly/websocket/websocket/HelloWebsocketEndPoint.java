package com.jelly.websocket.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jelly on 2017/3/17.
 */
public class HelloWebsocketEndPoint extends TextWebSocketHandler {
    private static final Logger log = LoggerFactory.getLogger(HelloWebsocketEndPoint.class);
    private Map<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        webSocketSessionMap.put(getHttpSessionIdAndWebSocketId(session), session);
        log.debug("======>HelloWebsocketEndPoint, ConnectionEstablished, httpSessionId={}, webSocketId={}, totalWebSocketSession={}",
                getHttpSessionId(session), session.getId(), webSocketSessionMap.size());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        webSocketSessionMap.remove(getHttpSessionIdAndWebSocketId(session));
        log.debug("======>HelloWebsocketEndPoint, ConnectionClosed, httpSessionId={}, webSocketId={}, totalWebSocketSession={}",
                getHttpSessionId(session), session.getId(), webSocketSessionMap.size());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
        session.sendMessage(returnMessage);
        log.debug("======>HelloWebsocketEndPoint, handleMessage, httpSessionId={}, webSocketId={}, receivedText={}",
                getHttpSessionId(session), session.getId(), message.getPayload());
    }

    private String getHttpSessionId(WebSocketSession session){
        String httpSessionId = null;
        httpSessionId = (String) session.getAttributes().get(WebsocketHandshakeInterceptor.HTTP_SESSION_KEY_MMY);
        return httpSessionId;
    }

    private String getHttpSessionIdAndWebSocketId(WebSocketSession session){
        String httpSessionId = null;
        httpSessionId = (String) session.getAttributes().get(WebsocketHandshakeInterceptor.HTTP_SESSION_KEY_MMY);
        return httpSessionId + "__" + session.getId();
    }
}
