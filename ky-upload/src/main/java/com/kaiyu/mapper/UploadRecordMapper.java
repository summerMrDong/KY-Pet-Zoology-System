package com.kaiyu.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaiyu.pojo.UploadRecordEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @Classname UploadRecordMapper
 * @Description TODO
 * @Date 2021/2/6 0006 上午 11:13
 * @Created by 董乙辰
 */
@Repository
public interface UploadRecordMapper extends Mapper<UploadRecordEntity>, InsertListMapper<UploadRecordEntity> {
    @Getter
    @Setter
    class Download{

        /**
         * 文件格式
         */
        private String fileFormat;

        /**
         * 存放的位置
         */
        private String location;

    }
    /**
     * 查询图片存放位置
     *
     * @param name
     * @return
     */
    @Select("SELECT location,file_format FROM ky_upload_record WHERE final_name = #{name}")
    Download selectByFinalName(String name);
}
