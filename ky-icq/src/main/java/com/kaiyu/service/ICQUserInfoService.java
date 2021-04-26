package com.kaiyu.service;

import com.kaiyu.dto.Info;
import com.kaiyu.enums.ResponseEnum;
import com.kaiyu.feign.UserFeignClient;
import com.kaiyu.mapper.ICQFriendsMapper;
import com.kaiyu.socket.ICQSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname ICQUserInfoService
 * @Description TODO
 * @Date 2021/3/25 0025 下午 3:25
 * @Created by 董乙辰
 */
@Service
public class ICQUserInfoService {

    private UserFeignClient userFeignClient;;

    @Autowired
    public ICQUserInfoService(UserFeignClient userFeignClient, ICQFriendsMapper icqFriendsMapper) {
        this.userFeignClient = userFeignClient;
    }

    /**
     * 查询附近人信息
     *
     * @param userId
     */
    public Object selPeopleNearby(Long userId) {
        ConcurrentHashMap<Long, List<Long>> nearbyMap = ICQSession.getPeopleNearbyMap();
        List<Long> ids = nearbyMap.get(userId);

        if (Objects.nonNull(ids)) {

            if (!ids.isEmpty()) {
                return userFeignClient.selUser(ids);
            }

            return Info.ok(0, "没有附近人");

        }

        return Info.ok(ResponseEnum.USER_LINE);
    }

}
