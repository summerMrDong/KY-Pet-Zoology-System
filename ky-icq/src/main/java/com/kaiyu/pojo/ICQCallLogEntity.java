package com.kaiyu.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaiyu.utils.DateTimeFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.Order;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author 董乙辰
 * @Date 2021-03-24 10:21:30
 * @Description ky_icq_call_log
 **/
@Data
@Table(name = "ky_icq_call_log")
@NoArgsConstructor
public class ICQCallLogEntity {

    /**
     * 主键id
     */
    @Id
	@KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 好友id
     */
    private Long friendId;

    /**
     * 创建时间
     */
    @Order
    @JsonFormat(pattern = DateTimeFormat.CN_TIME)
    private Date createTime;

    public ICQCallLogEntity(Long userId, Long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }
}