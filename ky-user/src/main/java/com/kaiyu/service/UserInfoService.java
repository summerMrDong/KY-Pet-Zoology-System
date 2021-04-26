package com.kaiyu.service;

import com.kaiyu.dto.Info;
import com.kaiyu.pojo.dto.UserDTO;
import com.kaiyu.enums.ExceptionEnum;
import com.kaiyu.error.KyException;
import com.kaiyu.mapper.WXUserMapper;
import com.kaiyu.pojo.entity.UserEntity;
import com.kaiyu.utils.BeanHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname UserInfoService
 * @Description TODO
 * @Date 2021/3/25 0025 下午 1:18
 * @Created by 董乙辰
 */
@Service
public class UserInfoService {

    private WXUserMapper wxUserMapper;

    @Autowired
    public UserInfoService(WXUserMapper wxUserMapper) {
        this.wxUserMapper = wxUserMapper;
    }

    /**
     * 查询用户信息
     *
     * @param userId
     */
    public Info<List<UserEntity>> selUser(List<Long> userId) {
        List<UserEntity> entities = wxUserMapper.selectByIdList(userId);

        if (entities.isEmpty()) {
            throw new KyException(ExceptionEnum.USER_NOT_EXIST);
        }

        return Info.ok(entities);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public Info<?> updUser(UserDTO user) {
        UserEntity entity = BeanHelper.copyProperties(user, UserEntity.class);
        wxUserMapper.updateByPrimaryKeySelective(entity);
        return Info.ok(wxUserMapper.selectByPrimaryKey(user.getUserId()));
    }
}
