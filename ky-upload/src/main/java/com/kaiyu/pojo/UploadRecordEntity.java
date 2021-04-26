package com.kaiyu.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author 董乙辰
 * @Date 2021-02-06 10:49:21
 * @Description ky_upload_record
 **/
@Data
@Table(name = "ky_upload_record")
public class UploadRecordEntity {

    /**
     * 主键自增
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * 用户名
     */
    @JsonIgnore
    private String username;

    /**
     * 文件类型
     */
    private Integer fileType;

    /**
     * 文件格式
     */
    private String fileFormat;

    /**
     * 默认文件名称
     */
    private String defaultName;

    /**
     * 最终文件名称
     */
    private String finalName;

    /**
     * 存放的位置
     */
    @JsonIgnore
    private String location;

    /**
     * 访问路径
     */
    private String previewUrl;

    /**
     * 下载路径
     */
    private String downloadUrl;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateTime;

}