package com.kaiyu.socket;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname ICQSession
 * @Description TODO
 * @Date 2021/3/23 0023 上午 11:11
 * @Created by 董乙辰
 */
@Slf4j
public abstract class ICQSession{

    /**
     * 当前在线连接数
     */
    protected static int onlineCount = 0;

    /**
     * 客户端ICQWebSocket对象
     */
    protected static final ConcurrentHashMap<Long, ICQWebSocket> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 坐标
     */
    protected static final ConcurrentHashMap<Long, List<Long>> peopleNearbyMap = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<Long, ICQWebSocket> getWebSocketMap() {
        return webSocketMap;
    }

    public static ConcurrentHashMap<Long, List<Long>> getPeopleNearbyMap() {
        return peopleNearbyMap;
    }
}
