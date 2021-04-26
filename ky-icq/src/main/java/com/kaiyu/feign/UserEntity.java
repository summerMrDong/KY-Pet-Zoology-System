package com.kaiyu.feign;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author
 * @Date 2021-03-18 14:57:43
 * @Description ky_user
 **/
@Data
@Table(name = "ky_user")
public class UserEntity {

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
     * 用户在开放平台的唯一标识符
     */
    private String unionId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 用户性别 0=未知 1=男性 2=女性
     */
    private Byte gender;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 所用的语言 en=英文 zh_cn=简体中文  zh_tw=繁体中文
     */
    private String language;

    /**
     * 0=停用 1=正常
     */
    private Boolean status;

    /**
     * 位置坐标经纬度
     */
    private String location;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}