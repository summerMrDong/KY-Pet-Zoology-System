package com.kaiyu.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaiyu.utils.DateTimeFormat;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author 董乙辰
 * @Date 2021-04-16 15:08:32
 * @Description ky_mall_spu
 **/
@Data
@Table(name = "ky_mall_spu")
public class MallGoodsSpuEntity {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品编码
     */
    private String coding;

    /**
     * 标题，促销信息
     */
    private String title;

    /**
     * 商品图片
     */
    private String images;

    /**
     * 店内分类
     */
    private Long cid;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 富文本
     */
    private String richText;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 总销量
     */
    private Integer totalSales;

    /**
     * 是否上架，0下架，1上架
     */
    private Boolean saleable;

    /**
     * 是否存在
     */
    @JsonIgnore
    private Boolean isExist;

    /**
     * skus
     */
    @Transient
    private List<MallGoodsSkuEntity> skuList;

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