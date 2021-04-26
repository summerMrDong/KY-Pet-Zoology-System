package com.kaiyu.function;

import com.kaiyu.socket.ICQSession;
import com.kaiyu.socket.ICQWebSocket;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname ICQCoreService
 * @Description TODO
 * @Date 2021/3/24 0024 下午 3:59
 * @Created by 董乙辰
 */
@Slf4j
@Getter
@Component
public class ICQCoreService extends ICQSession {

    private WebSocketOnOpen onOpen;
    private WebSocketOnMessage onMessage;
    private WebSocketOnClose onClose;
    private WebSocketOnError onError;

    @Autowired
    public ICQCoreService(WebSocketOnOpen onOpen, WebSocketOnMessage onMessage, WebSocketOnClose onClose, WebSocketOnError onError) {
        this.onOpen = onOpen;
        this.onMessage = onMessage;
        this.onClose = onClose;
        this.onError = onError;
    }

    public ICQWebSocket putWebSocket(Long key, ICQWebSocket webSocket) {
        return webSocketMap.put(key, webSocket);
    }

    public ICQWebSocket removeWebSocket(Long key) {
        return webSocketMap.remove(key);
    }

    public List<Long> putPeopleNearby(Long key, List<Long> ids) {
        return peopleNearbyMap.put(key, ids);
    }

    public List<Long> removePeopleNearby(Long key) {
        return peopleNearbyMap.remove(key);
    }

    public synchronized int getOnlineCount() {
        return onlineCount;
    }


    public synchronized void addOnlineCount() {
        onlineCount++;
        log.info("now open count:" + getOnlineCount());
    }

    public synchronized void subOnlineCount() {
        onlineCount--;
    }

}
