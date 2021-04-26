package com.kaiyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kaiyu.dto.Info;
import com.kaiyu.pojo.entity.UserEntity;
import com.kaiyu.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname UserLoginController
 * @Description TODO
 * @Date 2021/3/3 0003 下午 3:24
 * @Created by 董乙辰
 */
@RestController
public class UserLoginController {

    private UserLoginService userLoginService;

    @Autowired
    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    /**
     * 微信授权登录
     *
     * @param code
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<Info<Object>> login(@RequestBody UserEntity userInfo, @RequestParam String code) {
        return ResponseEntity.ok(userLoginService.login(userInfo, code));
    }


    @GetMapping("/test")
    public ResponseEntity<JSON> test() {
        System.out.println("进来了");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid", 1230);
        jsonObject.put("session_key", 1232);
        jsonObject.put("unionid", 1232);
        jsonObject.put("errcode", 0);
        jsonObject.put("errmsg", 1232);
        return ResponseEntity.ok(jsonObject);
    }

}
