package com.kaiyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.kaiyu.mapper")
public class UploadApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(UploadApplicationStart.class, args);
    }
}
