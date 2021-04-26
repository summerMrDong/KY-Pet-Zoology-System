package com.kaiyu.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author
 * @Date 2021-03-18 14:57:43
 * @Description ky_user
 **/
@Data
@Table(name = "ky_user")
public class ICQUserEntity {

    /**
     * 用户id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long userId;

    /**
     * 用户唯一标识
     */
    private String openId;

    /**
     * 位置坐标经纬度
     */
    private String location;

    /**
     * 是否在线
     */
    private Boolean onLine;
}