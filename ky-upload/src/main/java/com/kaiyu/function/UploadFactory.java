package com.kaiyu.function;

import com.kaiyu.pojo.UploadRecordEntity;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname UploadFactory
 * @Description 上传工厂
 * @Date 2021/2/4 0004 下午 2:34
 * @Created by 董乙辰
 */
@Component
@PropertySource(value = "classpath:application-file.properties")
public class UploadFactory  {

    @Value("${linux.default.directory}")
    private String linux_default_directory;

    @Value("${windows.default.directory}")
    private String windows_default_directory;

    /**
     * 获取图片上传功能
     *
     * @return
     */
    public UploadFunction<List<UploadRecordEntity>> getUploadImage() {
        return UploadImageImpl.getUploadImage(getUploadDirectory());
    }

    public String getUploadDirectory() {
        String OS = System.getProperty("os.name").toLowerCase();

        if (OS.contains("windows")) {
            return windows_default_directory;
        } else {
            return linux_default_directory;
        }
    }

}
