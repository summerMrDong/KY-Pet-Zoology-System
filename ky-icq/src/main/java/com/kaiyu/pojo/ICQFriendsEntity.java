package com.kaiyu.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaiyu.utils.DateTimeFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author 董乙辰
 * @Date 2021-03-24 10:24:27
 * @Description ky_icq_friends
 **/
@Data
@NoArgsConstructor
@Table(name = "ky_icq_friends")
public class ICQFriendsEntity {

    /**
     * 用户id
     */
    @Id
    private Long userId;

    /**
     * 好友id
     */
    private Long friendId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateTimeFormat.CN_TIME)
    private Date createTime;

    public ICQFriendsEntity(Long userId, Long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }
}