package com.kaiyu.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Classname DownloadType
 * @Description 下载类型
 * @Date 2021/2/19 0019 下午 1:53
 * @Created by 董乙辰
 */
@Slf4j
@Component
@PropertySource("classpath:application-file.properties")
public class DownloadConfig implements InitializingBean {

    /**
     * 下载请求路径
     */
    @Value("${download.domain.url}")
    private String downloadURL;

    /**
     * 预览请求路径
     */
    @Value("${preview.domain.url}")
    private String previewURL;

    /**
     * 下载前缀
     */
    @Value("${download.prefix}")
    private String downloadPrefix;

    @Override
    public void afterPropertiesSet() {
        log.info("\n文件下载请求路径："
                + String.format(downloadURL, "*.txt")
                + "\n文件预览请求路径："
                + String.format(previewURL, "**/*.txt")
                +"\n下载前缀："+downloadPrefix+" \n要求下载接口的前缀必须相同 例：@RequestMapping(\"/file/download\") 否则下载请求失败"
        );
    }

}
