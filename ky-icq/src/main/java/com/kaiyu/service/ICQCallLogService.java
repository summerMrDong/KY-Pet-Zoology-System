package com.kaiyu.service;

import com.kaiyu.dto.Info;
import com.kaiyu.feign.UserEntity;
import com.kaiyu.feign.UserFeignClient;
import com.kaiyu.mapper.ICQCallLogMapper;
import com.kaiyu.pojo.ICQCallLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname ICQCallLogService
 * @Description TODO
 * @Date 2021/3/26 0026 下午 4:30
 * @Created by 董乙辰
 */
@Service
public class ICQCallLogService {

    private ICQCallLogMapper icqCallLogMapper;
    private UserFeignClient userFeignClient;

    @Autowired
    public ICQCallLogService(ICQCallLogMapper icqCallLogMapper, UserFeignClient userFeignClient) {
        this.icqCallLogMapper = icqCallLogMapper;
        this.userFeignClient = userFeignClient;
    }

    /**
     * 查询通信记录
     *
     * @param userId
     */
    public Object selCallLog(Long userId) {
        ICQCallLogEntity entity = new ICQCallLogEntity();

        entity.setUserId(userId);

        List<Long> collect = icqCallLogMapper.select(entity).stream().map(ICQCallLogEntity::getFriendId).collect(Collectors.toList());

        if (collect.isEmpty()) {
            return Info.ok(new ArrayList<UserEntity>());
        }

        return userFeignClient.selUser(collect);
    }

    /**
     * 删除通信记录
     *
     * @param userId
     * @param friendId
     * @return
     */
    public void delCallLog(Long userId, Long friendId) {
        ICQCallLogEntity entity = new ICQCallLogEntity(userId, friendId);
        icqCallLogMapper.delete(entity);
    }

}
