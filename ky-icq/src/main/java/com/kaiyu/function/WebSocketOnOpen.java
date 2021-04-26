package com.kaiyu.function;

import com.kaiyu.socket.ICQSession;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @Classname WebSocketOpen
 * @Description TODO
 * @Date 2021/3/24 0024 下午 3:26
 * @Created by 董乙辰
 */

public abstract class WebSocketOnOpen extends ICQSession {

    /**
     * 更新用户经纬度
     *
     * @param userId
     * @param longitude
     * @param latitude
     */
    public abstract void updLocation(Long userId, double longitude, double latitude);

    /**
     * 搜索附近人
     *
     * @param userId
     * @param longitude
     * @param latitude
     */
    public abstract void peopleNearby(Long userId, double longitude, double latitude);

    /**
     * 签收邮件
     *
     * @param webSocketSession
     * @param userId
     */
    public abstract void getCallLog(Session webSocketSession, Long userId) throws IOException;

    /**
     * 上线状态
     */
    public abstract void onLine(Long userId);

}
