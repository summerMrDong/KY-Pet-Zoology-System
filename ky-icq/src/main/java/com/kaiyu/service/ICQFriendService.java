package com.kaiyu.service;

import com.kaiyu.dto.Info;
import com.kaiyu.enums.ExceptionEnum;
import com.kaiyu.enums.ResponseEnum;
import com.kaiyu.error.KyException;
import com.kaiyu.feign.UserFeignClient;
import com.kaiyu.mapper.ICQFriendsMapper;
import com.kaiyu.mapper.ICQUserMapper;
import com.kaiyu.pojo.ICQFriendsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Classname ICQFriendService
 * @Description TODO
 * @Date 2021/3/26 0026 下午 12:34
 * @Created by 董乙辰
 */
@Service
public class ICQFriendService {

    private ICQUserMapper icqUserMapper;
    private ICQFriendsMapper icqFriendsMapper;
    private UserFeignClient userFeignClient;

    @Autowired
    public ICQFriendService(ICQUserMapper icqUserMapper, ICQFriendsMapper icqFriendsMapper, UserFeignClient userFeignClient) {
        this.icqUserMapper = icqUserMapper;
        this.icqFriendsMapper = icqFriendsMapper;
        this.userFeignClient = userFeignClient;
    }

    /**
     * 添加关注
     *
     * @param userId
     * @param friendId
     * @return
     */
    public Info<String> addFriend(Long userId, Long friendId) {
        boolean user = icqUserMapper.existsWithPrimaryKey(userId);
        boolean friend = icqUserMapper.existsWithPrimaryKey(friendId);

        if (!user || !friend) {
            throw new KyException(ExceptionEnum.USER_NOT_EXIST);
        }

        ICQFriendsEntity entity = new ICQFriendsEntity(userId, friendId);
        ICQFriendsEntity relation = icqFriendsMapper.selectOne(entity);

        if (Objects.nonNull(relation)) {
            return Info.ok(ResponseEnum.USER_EXIST_FRIEND);
        }

        icqFriendsMapper.insertSelective(entity);
        return Info.ok();
    }

    /**
     * 取消关注
     *
     * @param userId
     * @param friendId
     */
    public void delFriend(Long userId, Long friendId) {
        icqFriendsMapper.delete(new ICQFriendsEntity(userId, friendId));
    }

    /**
     * 查询好友信息
     *
     * @param userId
     * @return
     */
    public Object selFriends(Long userId) {
        List<ICQFriendsEntity> friends = icqFriendsMapper.selectFriendsByUserId(userId);

        if (friends.isEmpty()) {
            return Info.ok(0, "该用户没有任何好友");
        }

        List<Long> ids = friends.stream().map(ICQFriendsEntity::getFriendId).collect(Collectors.toList());

        return userFeignClient.selUser(ids);
    }

    /**
     * 查询粉丝信息
     *
     * @param userId
     * @return
     */
    public Object selFans(Long userId) {
        List<ICQFriendsEntity> fans = icqFriendsMapper.selectFansByUserId(userId);

        if (fans.isEmpty()) {
            return Info.ok(0, "该用户没有粉丝");
        }

        List<Long> ids = fans.stream().map(ICQFriendsEntity::getUserId).collect(Collectors.toList());

        return userFeignClient.selUser(ids);
    }

    /**
     * 查询关注
     *
     * @param userId
     * @return
     */
    public Object selFollows(Long userId) {

        List<ICQFriendsEntity> follows = icqFriendsMapper.selectFollowsByUserId(userId);

        if (follows.isEmpty()) {
            return Info.ok(0, "该用户没有关注任何人");
        }

        List<Long> ids = follows.stream().map(ICQFriendsEntity::getFriendId).collect(Collectors.toList());

        return userFeignClient.selUser(ids);
    }

    /**
     * 是否已经关注
     * @param userId
     * @param friendId
     * @return
     */
    public Info<Boolean> isFollow(Long userId, Long friendId) {
        ICQFriendsEntity entity = new ICQFriendsEntity(userId,friendId);
        ICQFriendsEntity one = icqFriendsMapper.selectOne(entity);
        return Info.ok(Objects.nonNull(one));
    }

}
