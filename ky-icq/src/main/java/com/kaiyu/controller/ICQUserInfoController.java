package com.kaiyu.controller;

import com.kaiyu.dto.Info;
import com.kaiyu.service.ICQUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname ICQWebSocketController
 * @Description TODO
 * @Date 2021/3/25 0025 下午 12:51
 * @Created by 董乙辰
 */
@RestController
public class ICQUserInfoController {

    private ICQUserInfoService icqUserInfoService;

    @Autowired
    public ICQUserInfoController(ICQUserInfoService icqUserInfoService) {
        this.icqUserInfoService = icqUserInfoService;
    }

    /**
     * 查询附近人信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/sel/peopleNearby")
    public ResponseEntity<Object> selPeopleNearby(@RequestParam Long userId) {
        return ResponseEntity.ok(icqUserInfoService.selPeopleNearby(userId));
    }

}
