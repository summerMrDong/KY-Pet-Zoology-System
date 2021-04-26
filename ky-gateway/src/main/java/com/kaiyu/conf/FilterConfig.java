package com.kaiyu.conf;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "ky.filter")
public class FilterConfig implements InitializingBean {

    private List<String> allowPaths;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("\n"+ allowPaths);
    }
}