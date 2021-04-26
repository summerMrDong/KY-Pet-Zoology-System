package com.kaiyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan(basePackages = "com.kaiyu.mapper")
@EnableAsync
@EnableFeignClients
@SpringBootApplication
public class ICQApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(ICQApplicationStart.class, args);
    }
}
