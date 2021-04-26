package com.kaiyu.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Classname WXConfig
 * @Description TODO
 * @Date 2021/3/18 0018 上午 9:44
 * @Created by 董乙辰
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wx")
public class WXConfig {

    /**
     * 小程序 appId
     */
    private String appId;

    /**
     * 小程序 appSecret
     */
    private String secret;

    /**
     * 登录请求地址
     */
    private String wx_url;

}
