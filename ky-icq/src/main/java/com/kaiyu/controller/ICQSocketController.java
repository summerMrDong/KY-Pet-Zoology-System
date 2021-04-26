package com.kaiyu.controller;

import com.kaiyu.dto.Info;
import com.kaiyu.function.WebSocketOnOpen;
import com.kaiyu.service.ICQSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ICQSocketController
 * @Description TODO
 * @Date 2021/3/26 0026 上午 8:55
 * @Created by 董乙辰
 */
@RestController
public class ICQSocketController {

    private ICQSocketService icqSocketService;

    @Autowired
    public ICQSocketController(ICQSocketService icqSocketService) {
        this.icqSocketService = icqSocketService;
    }

    /**
     * 更新经纬度
     *
     * @param userId
     * @param longitude
     * @param latitude
     * @return
     */
    @PutMapping("/upd/location")
    public ResponseEntity<Info<String>> updLocation(
            @RequestParam Long userId,
            @RequestParam double longitude, double latitude
    ) {
        icqSocketService.updLocation(userId, longitude, latitude);
        return ResponseEntity.ok(Info.ok());
    }

    /**
     * 刷新附近人
     *
     * @return
     */
    @PutMapping("/upd/peopleNearby")
    public ResponseEntity<Info<String>> flushPeopleNearby(@RequestParam Long userId) {
        icqSocketService.flushPeopleNearby(userId);
        return ResponseEntity.ok(Info.ok());
    }

}
