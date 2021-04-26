package com.kaiyu.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaiyu.utils.DateTimeFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author 董乙辰
 * @Date 2021-04-02 13:12:56
 * @Description ky_pet_voice
 **/
@Data
@Table(name = "ky_pet_voice")
public class PetVoiceEntity {

    /**
     * 主键id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * 分类id
     */
    private Long classifyId;

    /**
     * 分类名称
     */
    private String classify;

    /**
     * 资源地址
     */
    private String url;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateTimeFormat.CN_TIME,timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = DateTimeFormat.CN_TIME,timezone = "GMT+8")
    private Date updateTime;

}