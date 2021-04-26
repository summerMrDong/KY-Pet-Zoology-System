package com.kaiyu.service;

import com.alibaba.fastjson.JSON;
import com.kaiyu.dto.Email;
import com.kaiyu.dto.Info;
import com.kaiyu.enums.ResponseEnum;
import com.kaiyu.feign.UserFeignClient;
import com.kaiyu.mapper.ICQEmailMapper;
import com.kaiyu.pojo.ICQEmailEntity;
import com.kaiyu.socket.ICQSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname ICQWebSocketService
 * @Description TODO
 * @Date 2021/2/21 0021 上午 11:27
 * @Created by 董乙辰
 */
@Service
public class ICQEmailService {


    private ICQEmailMapper icqEmailMapper;

    @Autowired
    public ICQEmailService(ICQEmailMapper icqEmailMapper) {
        this.icqEmailMapper = icqEmailMapper;
    }

    /**
     * 保存邮件信息
     *
     * @param email  邮件
     * @param status 状态 false = 未接收 true = 已接收
     */
    @Async
    public void addEmail(Email email, boolean status) {
        ICQEmailEntity entity = new ICQEmailEntity();

        entity.setSendId(email.getSendId());
        entity.setReceiveId(email.getReceiveId());
        entity.setType(email.getType());
        entity.setContent(email.getContent());
        entity.setStatus(status);
        icqEmailMapper.insertSelective(entity);
    }

    /**
     * 查询聊天记录
     *
     * @param sendId
     * @param receiveId
     */
    public Info<List<ICQEmailEntity>> selEmailAll(Long sendId, Long receiveId) {
        Example.Builder builder = Example.builder(ICQEmailEntity.class);
        Example build = builder.where(Sqls
                .custom()
                .andEqualTo("sendId", sendId)
                .andEqualTo("receiveId", receiveId)
                .orEqualTo("sendId", receiveId)
                .andEqualTo("receiveId", sendId)
        ).orderByAsc("createTime").build();

        return Info.ok(icqEmailMapper.selectByExample(build));
    }
}
