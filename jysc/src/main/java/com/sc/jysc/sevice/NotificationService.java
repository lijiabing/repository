package com.sc.jysc.sevice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jackbing on 2017/12/25.
 */
@Service
@Primary
public class NotificationService implements WebSocketHandler {


    private Logger logger= LoggerFactory.getLogger(NotificationService.class);
    ConcurrentHashMap<String,WebSocketSession> concurrentHashMap=new ConcurrentHashMap<String,WebSocketSession>();

    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if(!concurrentHashMap.containsKey(session.getPrincipal().toString())){
            concurrentHashMap.put(session.getPrincipal().toString(),session);
        }

    }

    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if(message instanceof TextMessage) {
            this.handleTextMessage(session, (TextMessage)message);
        } else if(message instanceof BinaryMessage) {
            this.handleBinaryMessage(session, (BinaryMessage)message);
        } else {
            if(!(message instanceof PongMessage)) {
                throw new IllegalStateException("Unexpected WebSocket message type: " + message);
            }

            this.handlePongMessage(session, (PongMessage)message);
        }

    }

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        session.sendMessage(message);
    }

    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
    }

    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
    }

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if(concurrentHashMap.containsKey(session.getPrincipal().toString())){
            concurrentHashMap.remove(session.getPrincipal().toString());
        }
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 通过userId，获取session，将message发到指定session
     * @param userId
     * @param message
     * @throws Exception
     */
    public void sendMessage(String userId,String message) throws Exception {
        if(concurrentHashMap.containsKey(userId)){

            this.handleTextMessage(concurrentHashMap.get(userId),new TextMessage(message));
        }else{
            logger.debug("concurrentHashMap not containts the object whith the keys of "+userId);
        }

    }

    public boolean isContainSession(String key){
        if(concurrentHashMap.containsKey(key)){
            return true;
        }else{
            return false;
        }
    }

    public ConcurrentHashMap<String, WebSocketSession> getConcurrentHashMap() {
        return concurrentHashMap;
    }

}
