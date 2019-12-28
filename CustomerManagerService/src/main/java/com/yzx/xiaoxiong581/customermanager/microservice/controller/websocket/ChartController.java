package com.yzx.xiaoxiong581.customermanager.microservice.controller.websocket;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaoxiong581
 */
@Slf4j
@ServerEndpoint("/v1/chart/{accountName}")
@RestController
public class ChartController {
    private Map<String, ChartController> charts = new ConcurrentHashMap<>();

    private WebSocketClient client;

    @OnOpen
    public void onOpen(@PathParam("accountName") String accountName, Session session) {
        charts.put(session.getId(), this);
        log.info("id: {}, accountName: {} add connect", session.getId(), accountName);
        try {
            session.getBasicRemote().sendText("welcome to join, " + accountName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client = new WebSocketClient(new URI("ws://192.168.137.106:29080/customermanager/v1/chart/" + accountName),
                    new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("client open connect success");
                }

                @Override
                public void onMessage(String s) {
                    System.out.println("receive: " + s);
                    try {
                        session.getBasicRemote().sendText(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("client connect is closed");
                }

                @Override
                public void onError(Exception e) {
                    System.out.println("client occur error " + e);
                }
            };
        } catch (Exception e) {
            System.out.println("websocket occur error " + e);
        }
        client.connect();
    }

    @OnClose
    public void onClose(CloseReason closeReason, Session session) {
        log.info("id: {} close connect, reason", session.getId(), closeReason.getReasonPhrase());
        charts.remove(session.getId());
        client.close();
    }

    @OnMessage
    public void onMessage(String text, Session session) {
        log.info("receive message from: {}, info: {}", session.getId(), text);
//        return "hi: " + text;
        while (!client.getReadyState().equals(ReadyState.OPEN)) {
        }
        this.client.send(text);
//        return "";
    }

    @OnError
    public void onError(Session session, Throwable e) {
        log.error("id {} occur error, err: {}", session.getId(), e.getMessage());
    }
}
