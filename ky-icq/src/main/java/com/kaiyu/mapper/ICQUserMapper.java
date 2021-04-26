package com.kaiyu.mapper;

import com.kaiyu.pojo.ICQUserEntity;
import org.apache.ibatis.annotations.Update;
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
public interface ICQUserMapper extends Mapper<ICQUserEntity>, SelectByIdListMapper<ICQUserEntity,Long> {

    /**
     * 更新用户坐标经纬度信息
     *
     * @param userId
     * @param location
     * @return
     */
    @Update("UPDATE ky_user SET location=#{location} WHERE user_id = #{userId}")
    int updLocation(Long userId, String location);
}












