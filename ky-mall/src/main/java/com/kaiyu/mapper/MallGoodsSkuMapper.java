package com.kaiyu.mapper;

import com.kaiyu.domain.entity.MallGoodsSkuEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Classname MallGoodsSkuMapper
 * @Description TODO
 * @Date 2021/4/19 0019 下午 3:59
 * @Created by 董乙辰
 */
@Repository
public interface MallGoodsSkuMapper extends Mapper<MallGoodsSkuEntity>, SelectByIdListMapper<MallGoodsSkuEntity,Long> {

    List<MallGoodsSkuEntity> selectByIds(List<Long> spuIdS);
}
