package com.kaiyu.mapper;

import com.kaiyu.dto.Email;
import com.kaiyu.pojo.ICQEmailEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;

import java.util.List;

/**
 * @Classname ChatMsgMapper
 * @Description TODO
 * @Date 2021/2/22 0022 上午 9:04
 * @Created by 董乙辰
 */
@Repository
public interface ICQEmailMapper extends Mapper<ICQEmailEntity>, DeleteByIdListMapper<ICQEmailEntity,Long>  {

    /**
     * 查询聊天记录
     *
     * @param email
     * @return
     */
    List<Email> LookTwoUserMsg(@Param("chatMsg") Email email);
}
