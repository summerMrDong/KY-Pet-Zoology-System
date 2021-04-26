package com.kaiyu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Classname UserFeignClient
 * @Description TODO
 * @Date 2021/3/25 0025 下午 1:50
 * @Created by 董乙辰
 */
@FeignClient(value = "user-service")
public interface UserFeignClient {

    @GetMapping("/sel/user")
    Object selUser(@RequestParam List<Long> userId);
}
