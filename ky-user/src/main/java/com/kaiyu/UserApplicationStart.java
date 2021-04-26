package com.kaiyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Classname UserApplicationStart
 * @Description TODO
 * @Date 2021/3/3 0003 下午 3:32
 * @Created by 董乙辰
 */
@SpringBootApplication
@MapperScan(basePackages = "com.kaiyu.mapper")
public class UserApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(UserApplicationStart.class, args);
    }
}
