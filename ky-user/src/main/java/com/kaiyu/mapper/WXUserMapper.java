package com.kaiyu.mapper;

import com.kaiyu.pojo.entity.UserEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Classname WXUserMapper
 * @Description TODO
 * @Date 2021/3/3 0003 下午 3:31
 * @Created by 董乙辰
 */
@Repository
public interface WXUserMapper extends Mapper<UserEntity>, SelectByIdListMapper<UserEntity,Long> {

    @Select("SELECT user_id,open_id,union_id,nick_name,avatar_url,gender,country,province,city,language,status,create_time,update_time "
            + "FROM ky_user "
            + "WHERE open_id = #{openId}"
    )
    UserEntity selectByOpenId(String openId);
}












