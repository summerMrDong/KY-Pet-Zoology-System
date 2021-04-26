package com.kaiyu.function.impl;

import com.kaiyu.function.WebSocketOnClose;
import com.kaiyu.mapper.ICQUserMapper;
import com.kaiyu.pojo.ICQUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname WebSocketCloseImpl
 * @Description TODO
 * @Date 2021/3/25 0025 上午 9:44
 * @Created by 董乙辰
 */
@Slf4j
@Component
public class WebSocketOnCloseImpl extends WebSocketOnClose {

    private ICQUserMapper icqUserMapper;

    @Autowired
    public WebSocketOnCloseImpl(ICQUserMapper icqUserMapper) {
        this.icqUserMapper = icqUserMapper;
    }

    @Override
    public void offLine(Long userId) {
        ICQUserEntity entity = new ICQUserEntity();
        entity.setUserId(userId);
        entity.setOnLine(false);
        icqUserMapper.updateByPrimaryKeySelective(entity);
    }

}
