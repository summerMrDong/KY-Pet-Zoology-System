package com.kaiyu.pojo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaiyu.utils.DateTimeFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author 董乙辰
 * @Date 2021-03-31 14:15:32
 * @Description ky_pet
 **/
@Data
@Table(name = "ky_pet")
public class PetEntity {

    /**
     * id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 宠物名称
     */
    private String name;

    /**
     * 分类id
     */
    private Long classifyId;

    /**
     * 分类名
     */
    private String classify;

    /**
     * 品种id
     */
    private Long breedId;

    /**
     * 品种名
     */
    private String breed;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 0=母 1=公
     */
    private Integer sex;

    /**
     * 宠物图片
     */
    private String imgUrl;

    /**
     * 绑定的硬件id
     */
    private String gpsId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateTimeFormat.CN_TIME, timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = DateTimeFormat.CN_TIME, timezone = "GMT+8")
    private Date updateTime;

}