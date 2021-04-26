package com.kaiyu.service;

import com.kaiyu.function.WebSocketOnOpen;
import com.kaiyu.mapper.ICQUserMapper;
import com.kaiyu.pojo.ICQUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname ICQSocketService
 * @Description TODO
 * @Date 2021/3/26 0026 上午 8:56
 * @Created by 董乙辰
 */
@Service
public class ICQSocketService {

    private WebSocketOnOpen webSocketOnOpen;
    private ICQUserMapper icqUserMapper;

    @Autowired
    public ICQSocketService(WebSocketOnOpen webSocketOnOpen, ICQUserMapper icqUserMapper) {
        this.webSocketOnOpen = webSocketOnOpen;
        this.icqUserMapper = icqUserMapper;
    }

    /**
     * 更新经纬度
     * @param userId
     * @param longitude
     * @param latitude
     */
    public void updLocation(Long userId, double longitude, double latitude) {
        webSocketOnOpen.updLocation(userId, longitude, latitude);
    }

    /**
     * 刷新附近人
     * @param userId
     */
    public void flushPeopleNearby(Long userId) {
        ICQUserEntity entity = icqUserMapper.selectByPrimaryKey(userId);
        String location = entity.getLocation();
        String[] split = location.split("\\|");
        webSocketOnOpen.peopleNearby(userId,Double.parseDouble(split[0]),Double.parseDouble(split[1]));
    }

}
