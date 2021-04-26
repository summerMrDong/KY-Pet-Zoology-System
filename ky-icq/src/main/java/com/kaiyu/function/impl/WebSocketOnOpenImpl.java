package com.kaiyu.function.impl;

import com.kaiyu.dto.Email;
import com.kaiyu.function.WebSocketOnOpen;
import com.kaiyu.mapper.ICQCallLogMapper;
import com.kaiyu.mapper.ICQEmailMapper;
import com.kaiyu.mapper.ICQFriendsMapper;
import com.kaiyu.mapper.ICQUserMapper;
import com.kaiyu.pojo.ICQEmailEntity;
import com.kaiyu.pojo.ICQUserEntity;
import com.kaiyu.socket.ICQSession;
import com.kaiyu.utils.BaiduUtil;
import com.kaiyu.utils.BeanHelper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname WebSocketOnCloseImpl
 * @Description TODO
 * @Date 2021/3/24 0024 下午 3:35
 * @Created by 董乙辰
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "icq")
public class WebSocketOnOpenImpl extends WebSocketOnOpen {

    /**
     * 距离
     */
    private Integer distance;
    private ICQUserMapper icqUserMapper;
    private ICQEmailMapper icqEmailMapper;

    @Autowired
    public WebSocketOnOpenImpl(ICQEmailMapper icqEmailMapper, ICQUserMapper icqUserMapper) {
        this.icqUserMapper = icqUserMapper;
        this.icqEmailMapper = icqEmailMapper;
    }


    @Async
    @Override
    public void updLocation(Long userId, double longitude, double latitude) {
        icqUserMapper.updLocation(userId, longitude + "|" + latitude);
    }

    @Async
    @Override
    public void peopleNearby(Long userId, double longitude, double latitude) {
        Enumeration<Long> keys = webSocketMap.keys();
        List<Long> openIds = new ArrayList<>();

        while (keys.hasMoreElements()) {
            Long aLong = keys.nextElement();

            if (!aLong.equals(userId)) {
                openIds.add(aLong);
            }

        }

        List<Long> ids = new ArrayList<>();

        if (!openIds.isEmpty()) {
            List<ICQUserEntity> userInfos = icqUserMapper.selectByIdList(openIds);

            ids = userInfos.stream().parallel().filter((a) -> {
                String[] xy = a.getLocation().split("\\|");
                long distance = BaiduUtil.getDistance(
                        longitude, latitude, Double.parseDouble(xy[0]), Double.parseDouble(xy[1])
                );

                return distance <= this.distance;
            }).map(ICQUserEntity::getUserId).collect(Collectors.toList());
        }

        log.info("\n用户:" + userId + "\n附近人列表:" + ids);
        peopleNearbyMap.put(userId, ids);
    }

    @Async
    @Override
    public void getCallLog(Session webSocketSession, Long userId) throws IOException {
        List<ICQEmailEntity> entities = icqEmailMapper.select(new ICQEmailEntity(userId, false));

        if (!entities.isEmpty()) {
            RemoteEndpoint.Basic basicRemote = webSocketSession.getBasicRemote();
            Class<Email> aClass = Email.class;

            for (ICQEmailEntity entity : entities) {
                Email email = BeanHelper.copyProperties(entity, aClass);

                basicRemote.sendText(email.toString());

                Long id = entity.getId();
                entity = new ICQEmailEntity();

                entity.setId(id);
                entity.setStatus(true);
                icqEmailMapper.updateByPrimaryKeySelective(entity);
            }
        }
    }

    @Override
    public void onLine(Long userId) {
        ICQUserEntity entity = new ICQUserEntity();
        entity.setUserId(userId);
        entity.setOnLine(true);
        icqUserMapper.updateByPrimaryKeySelective(entity);
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
