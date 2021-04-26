package com.kaiyu.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaiyu.utils.DateTimeFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname MallGoodsSkuEntity
 * @Description TODO
 * @Date 2021/4/19 0019 下午 3:50
 * @Created by 董乙辰
 */
@Data
@Table(name = "ky_mall_sku")
public class MallGoodsSkuEntity {

    /**
     * sku id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * spu id
     */
    private Long spuId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品的图片，多个图片以‘,’分割
     */
    private String images;

    /**
     * 库存
     */
    private Long stock;

    /**
     * 重量
     */
    private Float kg;

    /**
     * 进货价 0.00
     */
    private BigDecimal purchasePrice;

    /**
     * 原价 0.00
     */
    private BigDecimal originalPrice;

    /**
     * 销售价 0.00
     */
    private BigDecimal salePrice;

    /**
     * 排序值
     */
    @JsonIgnore
    private Integer sort;

    /**
     * 是否有效，0无效，1有效
     */
    @JsonIgnore
    private Boolean enable;

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
