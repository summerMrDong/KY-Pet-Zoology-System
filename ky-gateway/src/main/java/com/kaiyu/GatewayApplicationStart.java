package com.kaiyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.kaiyu.mapper")
@EnableZuulProxy
@SpringBootApplication
public class GatewayApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplicationStart.class, args);
    }
}
