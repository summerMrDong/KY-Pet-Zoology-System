package com.kaiyu.pojo.entity;

import java.util.Date;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author 董乙辰
 * @Date 2021-03-30 14:24:39
 * @Description ky_pet_type
 **/
@Data
@Table(name = "sys_pet_type")
public class PetTypeEntity {

    /**
     * 主键id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * 分类
     */
    private String classify;

    /**
     * 品种
     */
    private String breed;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

}