package com.kaiyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Classname MallApplicationStart
 * @Description TODO
 * @Date 2021/4/14 0014 下午 1:48
 * @Created by 董乙辰
 */
@MapperScan(basePackages = "com.kaiyu.mapper")
@EnableEurekaClient
@SpringBootApplication
public class MallApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(MallApplicationStart.class, args);
    }
}
