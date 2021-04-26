package com.kaiyu.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author
 * @Date 2021-03-18 14:57:43
 * @Description ky_user
 **/
@Data
public class UserDTO {

    /**
     * 用户id
     */
    @NotNull
    private Long userId;


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

}