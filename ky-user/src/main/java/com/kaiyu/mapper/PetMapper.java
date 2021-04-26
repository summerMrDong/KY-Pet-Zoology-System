package com.kaiyu.mapper;

import com.kaiyu.pojo.entity.PetEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Classname UserPetMapper
 * @Description TODO
 * @Date 2021/3/31 0031 下午 4:23
 * @Created by 董乙辰
 */
@Repository
public interface PetMapper extends Mapper<PetEntity> {
}
