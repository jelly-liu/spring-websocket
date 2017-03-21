package com.jelly.websocket.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * Created by jelly on 2017/3/17.
 */
public class WebsocketHandshakeInterceptor implements HandshakeInterceptor {
    private static final Logger log = LoggerFactory.getLogger(WebsocketHandshakeInterceptor.class);

    public static final String HTTP_SESSION_KEY_MMY = "HTTP_SESSION_KEY_MMY";
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) serverHttpRequest;
        String httpSession = servletServerHttpRequest.getServletRequest().getSession().getId();
        map.put(HTTP_SESSION_KEY_MMY, httpSession);
        log.debug("WebsocketHandshakeInterceptor, beforeHandshake, httpSessionId={}", httpSession);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    }
}
