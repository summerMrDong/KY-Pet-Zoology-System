package com.kaiyu.pojo.entity;

import java.util.Date;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author
 * @Date 2021-03-18 12:59:59
 * @Description ky_wx_session
 **/
@Data
@Table(name = "ky_wx_session")
public class WXSessionEntity {

    /**
     * 微信用户唯一标识
     */
    @Id
    private String openid;

    /**
     * 用户在开放平台的唯一标识符
     */
    private String unionId;

    /**
     * 会话密钥
     */
    private String sessionKey;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}