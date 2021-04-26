package com.kaiyu.controller;

import com.kaiyu.dto.Info;
import com.kaiyu.pojo.ICQCallLogEntity;
import com.kaiyu.service.ICQCallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname ICQCallLogController
 * @Description TODO
 * @Date 2021/3/26 0026 下午 4:29
 * @Created by 董乙辰
 */
@RestController
public class ICQCallLogController {

    private ICQCallLogService icqCallLogService;

    @Autowired
    public ICQCallLogController(ICQCallLogService icqCallLogService) {
        this.icqCallLogService = icqCallLogService;
    }

    /**
     * 查询通信记录
     *
     * @param userId
     * @return
     */
    @GetMapping("/sel/callLog")
    public ResponseEntity<Object> selCallLog(@RequestParam Long userId) {
        return ResponseEntity.ok(icqCallLogService.selCallLog(userId));
    }

    /**
     * 删除通信记录
     * @param userId
     * @param friendId
     * @return
     */
    @DeleteMapping("/del/callLog")
    public ResponseEntity<Info<String>> delCallLog(@RequestParam Long userId,@RequestParam Long friendId){
        icqCallLogService.delCallLog(userId,friendId);
        return ResponseEntity.ok(Info.ok());
    }

}
