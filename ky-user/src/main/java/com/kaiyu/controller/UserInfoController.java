package com.kaiyu.controller;

import com.kaiyu.dto.Info;
import com.kaiyu.pojo.dto.UserDTO;
import com.kaiyu.pojo.entity.UserEntity;
import com.kaiyu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Classname UserInfoController
 * @Description TODO
 * @Date 2021/3/25 0025 下午 1:12
 * @Created by 董乙辰
 */
@Validated
@RestController
public class UserInfoController {

    private UserInfoService infoService;

    @Autowired
    public UserInfoController(UserInfoService infoService) {
        this.infoService = infoService;
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("/sel/user")
    public ResponseEntity<Info<List<UserEntity>>> selUser(
            @RequestParam @NotEmpty List<Long> userId
    ) {
        return ResponseEntity.ok(infoService.selUser(userId));
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/upd/user")
    public ResponseEntity<Info<?>> updUser(@RequestBody @Valid UserDTO user){
        return ResponseEntity.ok(infoService.updUser(user));
    }

}
