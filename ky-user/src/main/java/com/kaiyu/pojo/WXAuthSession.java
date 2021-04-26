package com.kaiyu.pojo;

import lombok.Data;

/**
 * @Classname WX_AuthSession
 * @Description 请求微信授权返回的用户信息
 * @Date 2021/3/18 0018 上午 11:01
 * @Created by 董乙辰
 */
@Data
public class WXAuthSession {

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String session_key;

    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回
     */
    private String unionid;

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;
}
