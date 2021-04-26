package com.kaiyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author 董乙辰
 * @Date 2021/3/19
 * @Description Eureka注册中心.
 **/

@EnableEurekaServer
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class RegistryApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(RegistryApplicationStart.class, args);
    }
}
