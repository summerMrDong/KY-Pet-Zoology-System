package com.kaiyu.pojo;

import lombok.Data;

/**
 * @Author
 * @Date 2021-03-18 14:57:43
 * @Description ky_user
 **/
@Data
public class UserInfo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户唯一标识
     */
    private String openId;

    /**
     * 用户在开放平台的唯一标识符
     */
    private String unionId;

}