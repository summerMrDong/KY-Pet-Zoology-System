package com.kaiyu.function;

import com.kaiyu.dto.Email;
import com.kaiyu.socket.ICQSession;

/**
 * @Classname WebSocketOpen
 * @Description TODO
 * @Date 2021/3/24 0024 下午 3:26
 * @Created by 董乙辰
 */
public abstract class WebSocketOnMessage extends ICQSession {

    /**
     * 添加通信记录
     *
     * @param userId
     * @param receiveId
     */
    public abstract void addCallLog(Long userId, Long receiveId);

    /**
     * 发送邮件
     *
     * @param email 消息对象
     */
    public abstract void sendToUser(Long userId, Email email);

}
