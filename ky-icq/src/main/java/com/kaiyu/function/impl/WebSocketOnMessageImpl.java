package com.kaiyu.function.impl;

import com.kaiyu.dto.Email;
import com.kaiyu.function.WebSocketOnMessage;
import com.kaiyu.mapper.ICQCallLogMapper;
import com.kaiyu.mapper.ICQFriendsMapper;
import com.kaiyu.pojo.ICQCallLogEntity;
import com.kaiyu.pojo.ICQFriendsEntity;
import com.kaiyu.service.ICQEmailService;
import com.kaiyu.utils.EmojiFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @Classname WebSoketOnMessageImpl
 * @Description TODO
 * @Date 2021/3/25 0025 上午 9:24
 * @Created by 董乙辰
 */
@Slf4j
@Component
public class WebSocketOnMessageImpl extends WebSocketOnMessage {

    private ICQCallLogMapper icqCallLogMapper;
    private ICQEmailService icqEmailService;
    private ICQFriendsMapper icqFriendsMapper;

    @Autowired
    public WebSocketOnMessageImpl(ICQCallLogMapper icqCallLogMapper, ICQEmailService icqEmailService, ICQFriendsMapper icqFriendsMapper) {
        this.icqCallLogMapper = icqCallLogMapper;
        this.icqEmailService = icqEmailService;
        this.icqFriendsMapper = icqFriendsMapper;
    }

    /**
     * 添加通信记录
     *
     * @param userId
     * @param receiveId
     */
    @Async
    @Override
    public void addCallLog(Long userId, Long receiveId) {
        Example.Builder builder = Example.builder(ICQCallLogEntity.class);
        Example build = builder.where(Sqls
                .custom()
                .andEqualTo("userId", userId)
                .andEqualTo("friendId", receiveId)
                .orEqualTo("userId", receiveId)
                .andEqualTo("friendId", userId)
        ).build();
        List<ICQCallLogEntity> entities = icqCallLogMapper.selectByExample(build);

        if (entities.isEmpty()) {
            icqCallLogMapper.insertSelective(new ICQCallLogEntity(userId, receiveId));
            icqCallLogMapper.insertSelective(new ICQCallLogEntity(receiveId, userId));
            return;
        }

        if (entities.size() > 1) return;

        if (entities.get(0).getUserId().equals(userId)) {
            icqCallLogMapper.insertSelective(new ICQCallLogEntity(receiveId, userId));
        } else {
            icqCallLogMapper.insertSelective(new ICQCallLogEntity(userId, receiveId));
        }

    }

    /**
     * 发送邮件
     *
     * @param email 消息对象
     */
    @Async
    @Override
    public void sendToUser(Long userId, Email email) {
        Long receiveId = email.getReceiveId();

        email.setSendId(userId);
        email.setContent(EmojiFilter.filterEmoji(email.getContent()));

        try {

            if (webSocketMap.containsKey(receiveId)) {
                Session socketSession = webSocketMap.get(receiveId).getWebSocketSession();
                socketSession.getBasicRemote().sendText(email.toString());
                icqEmailService.addEmail(email, true);
            } else {

                if (!isMeet(userId, receiveId)) {
                    return;
                }

                icqEmailService.addEmail(email, false);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否(关联,相遇)
     *
     * @param userId
     * @param receiveId
     * @return
     */
    private boolean isMeet(Long userId, Long receiveId) {

        if (peopleNearbyMap.get(userId).contains(receiveId)) {
            return true;
        }

        if (icqFriendsMapper.selectCount(new ICQFriendsEntity(userId, receiveId)) > 0) {
            return true;
        }

        return icqCallLogMapper.selectCount(new ICQCallLogEntity(userId, receiveId)) > 0;
    }
}
