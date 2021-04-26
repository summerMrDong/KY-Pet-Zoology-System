package com.kaiyu.pojo;

import java.util.Date;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author 董乙辰
 * @Date 2021-04-02 17:17:18
 * @Description ky_pet_dictionaries
 **/
@Data
@Table(name = "ky_pet_dictionaries")
public class PetDictionariesEntity {

    /**
     * 主键id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * 单词
     */
    private String word;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}