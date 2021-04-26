package com.kaiyu.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.kaiyu.utils.DateTimeFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Email {

    /**
     * 发件人
     */
    private Long sendId;

    /**
     * 收件人
     */
    private Long receiveId;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 消息
     */
    private String content;

    /**
     * 信息创建的时间
     */
    @JSONField(format = DateTimeFormat.CN_TIME)
    private Date createTime = new Date();

    @Override
    public String toString() {
        return JSON.toJSON(this).toString();
    }
}