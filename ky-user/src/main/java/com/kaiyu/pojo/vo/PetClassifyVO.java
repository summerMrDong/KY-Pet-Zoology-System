package com.kaiyu.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaiyu.utils.DateTimeFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

/**
 * @Classname PetClassifyDTO
 * @Description TODO
 * @Date 2021/3/30 0030 下午 2:34
 * @Created by 董乙辰
 */
@Data
public class PetClassifyVO {

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
     * 创建时间
     */
    @JsonFormat(pattern = DateTimeFormat.CN_TIME,timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = DateTimeFormat.CN_TIME,timezone = "GMT+8")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

}
