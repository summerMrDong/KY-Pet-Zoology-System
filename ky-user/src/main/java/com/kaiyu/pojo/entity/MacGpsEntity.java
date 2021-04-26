package com.kaiyu.pojo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaiyu.utils.DateTimeFormat;
import lombok.Data;

/**
 * @Author 董乙辰
 * @Date 2021-04-01 14:34:32
 * @Description ky_gps
 **/
@Data
public class MacGpsEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 硬件名称
     */
    private String name;

    /**
     * 坐标经纬度
     */
    private String location;

    /**
     * 硬件信息
     */
    private String mac;

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