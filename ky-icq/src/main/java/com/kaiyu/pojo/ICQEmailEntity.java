package com.kaiyu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaiyu.utils.DateTimeFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.Order;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "ky_icq_email")
@NoArgsConstructor
public class ICQEmailEntity {

    /**
     * 主键id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * 发件人
     */
    private Long sendId;

    /**
     * 收件人
     */
    private Long receiveId;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 消息
     */
    private String content;

    /**
     * 信息创建的时间
     */
    @Order
    @JsonFormat(pattern = DateTimeFormat.CN_TIME,timezone = "GMT+8")
    private Date createTime;

    public ICQEmailEntity(Long receiveId, Boolean status) {
        this.receiveId = receiveId;
        this.status = status;
    }

}