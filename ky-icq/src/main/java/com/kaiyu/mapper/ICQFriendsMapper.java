package com.kaiyu.mapper;

import com.kaiyu.pojo.ICQFriendsEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.select.ExistsWithPrimaryKeyMapper;

import java.util.List;

/**
 * @Classname ICQFriendsMapper
 * @Description TODO
 * @Date 2021/3/24 0024 上午 10:27
 * @Created by 董乙辰
 */
@Repository
public interface ICQFriendsMapper extends Mapper<ICQFriendsEntity>, ExistsWithPrimaryKeyMapper<ICQFriendsEntity> {

    @Select("SELECT user_id,friend_id FROM ky_icq_friends WHERE friend_id = #{userId}")
    List<ICQFriendsEntity> selectFansByUserId(Long userId);

    @Select("SELECT * FROM `ky_icq_friends` a INNER JOIN `ky_icq_friends` b WHERE a.user_id = #{userId} HAVING (a.user_id=b.friend_id) and (b.user_id = a.friend_id) ")
    List<ICQFriendsEntity> selectFriendsByUserId(Long userId);

    @Select("SELECT user_id,friend_id FROM ky_icq_friends WHERE user_id = #{userId}")
    List<ICQFriendsEntity> selectFollowsByUserId(Long userId);
}
