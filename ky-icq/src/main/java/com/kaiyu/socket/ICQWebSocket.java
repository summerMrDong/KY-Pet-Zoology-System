package com.kaiyu.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kaiyu.dto.Email;
import com.kaiyu.dto.Info;
import com.kaiyu.error.KyBigException;
import com.kaiyu.function.ICQCoreService;
import com.kaiyu.function.WebSocketOnMessage;
import com.kaiyu.function.WebSocketOnOpen;
import com.kaiyu.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Slf4j
@Controller
@ServerEndpoint(value = "/websocket/{longitude}/{latitude}/{token}")
public class ICQWebSocket {

    private Long userId;
    private Session WebSocketSession;
    private static ICQCoreService icqCore;

    @Autowired
    public void setCoreService(ICQCoreService coreService) {
        ICQWebSocket.icqCore = coreService;
    }

    @OnOpen
    public void onOpen(
            @PathParam(value = "longitude") double longitude,
            @PathParam(value = "latitude") double latitude,
            @PathParam(value = "token") String token,
            Session webSocketSession
    ) throws KyBigException, IOException {
        JSONObject tokenInfo = new JSONObject(JWTUtils.decode(token));
        userId = tokenInfo.getJSONObject(JWTUtils.PAYLOAD).getLong("userId");
        this.WebSocketSession = webSocketSession;

        icqCore.putWebSocket(userId, this);

        WebSocketOnOpen onOpen = icqCore.getOnOpen();

        onOpen.onLine(userId);
        onOpen.updLocation(userId, longitude, latitude);
        onOpen.peopleNearby(userId, longitude, latitude);
        onOpen.getCallLog(webSocketSession, userId);
        icqCore.addOnlineCount();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        WebSocketOnMessage onMessage = icqCore.getOnMessage();
        Email email = JSON.parseObject(message, Email.class);

        onMessage.sendToUser(userId, email);
        onMessage.addCallLog(userId, email.getReceiveId());
    }

    @OnError
    public void onError(Session session, Throwable error) throws IOException {
        error.printStackTrace();
        KyBigException e = (KyBigException) error;
        session.getBasicRemote().sendText(Info.ok(e.getStatus(), e.getMessage()).toString());
    }

    @OnClose
    public void onClose() {
        icqCore.getOnClose().offLine(userId);
        icqCore.removeWebSocket(userId);
        icqCore.removePeopleNearby(userId);
        icqCore.subOnlineCount();
    }

    public Session getWebSocketSession() {
        return WebSocketSession;
    }
}

