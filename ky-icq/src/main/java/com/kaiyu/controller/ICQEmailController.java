package com.kaiyu.controller;

import com.kaiyu.dto.Info;
import com.kaiyu.pojo.ICQEmailEntity;
import com.kaiyu.service.ICQEmailService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Classname ICQEmailController
 * @Description TODO
 * @Date 2021/3/26 0026 下午 12:38
 * @Created by 董乙辰
 */
@Validated
@RestController
public class ICQEmailController {

    private ICQEmailService icqEmailService;

    @Autowired
    public ICQEmailController(ICQEmailService icqEmailService) {
        this.icqEmailService = icqEmailService;
    }

    /**
     * 查询聊天记录
     * @return
     */
    @GetMapping("/sel/emails")
    public ResponseEntity<Info<List<ICQEmailEntity>>> selEmailAll(@RequestParam Long sendId, @RequestParam Long receiveId){
        return ResponseEntity.ok(icqEmailService.selEmailAll(sendId,receiveId));
    }

}
