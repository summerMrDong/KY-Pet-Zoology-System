package com.kaiyu.pojo.entity;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Classname SysTest
 * @Description TODO
 * @Date 2021/3/2 0002 上午 10:27
 * @Created by 董乙辰
 */
@Data
@Table(name = "ky_gps")
public class GpsEntity {

    /**
     * gpdId
     */
    @Id
    private String id;

    /**
     * 硬件名称
     */
    private String name;

    /**
     * 经纬度
     */
    private String location;

    /**
     * 温度
     */
    private Integer temp;

}
