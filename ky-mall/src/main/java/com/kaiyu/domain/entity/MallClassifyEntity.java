package com.kaiyu.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaiyu.utils.DateTimeFormat;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname MallClassifyEntity
 * @Description TODO
 * @Date 2021/4/20 0020 上午 9:47
 * @Created by 董乙辰
 */
@Data
@Table(name = "ky_mall_classify")
public class MallClassifyEntity {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父id
     */
    private Long parentId;

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

    @Transient
    private List<MallClassifyEntity> subClassify;

    @Transient
    private List<MallGoodsSpuEntity> goods = new ArrayList<>();

}
