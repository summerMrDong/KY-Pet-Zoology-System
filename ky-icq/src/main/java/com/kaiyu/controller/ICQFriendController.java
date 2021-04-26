package com.kaiyu.controller;

import com.kaiyu.dto.Info;
import com.kaiyu.service.ICQFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname ICQFriendController
 * @Description TODO
 * @Date 2021/3/26 0026 上午 11:39
 * @Created by 董乙辰
 */
@RestController
public class ICQFriendController {

    private ICQFriendService icqFriendService;

    @Autowired
    public ICQFriendController(ICQFriendService icqFriendService) {
        this.icqFriendService = icqFriendService;
    }

    /**
     * 添加关注
     *
     * @return
     */
    @PostMapping("/add/friend")
    public ResponseEntity<Info<String>> addFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        return ResponseEntity.ok(icqFriendService.addFriend(userId, friendId));
    }

    /**
     * 取消关注
     * @param userId
     * @param friendId
     * @return
     */
    @DeleteMapping("/del/friend")
    public ResponseEntity<Info<String>> delFriend(@RequestParam Long userId,@RequestParam Long friendId){
        icqFriendService.delFriend(userId,friendId);
        return ResponseEntity.ok(Info.ok());
    }


    /**
     * 查询好友信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/sel/friends")
    public ResponseEntity<Object> selFriends(@RequestParam Long userId) {
        return ResponseEntity.ok(icqFriendService.selFriends(userId));
    }

    /**
     * 查询粉丝信息
     * @param userId
     * @return
     */
    @GetMapping("/sel/fans")
    public ResponseEntity<Object> selFans(@RequestParam Long userId){
        return ResponseEntity.ok(icqFriendService.selFans(userId));
    }

    /**
     * 查询关注
     * @param userId
     * @return
     */
    @GetMapping("/sel/follows")
    public ResponseEntity<Object> selFollows(@RequestParam Long userId){
        return ResponseEntity.ok(icqFriendService.selFollows(userId));
    }

    /**
     * 是否已经关注
     * @param userId
     * @param friendId
     * @return
     */
    @GetMapping("/sel/isFollow")
    public ResponseEntity<Info<Boolean>> isFollow(@RequestParam Long userId,@RequestParam Long friendId){
        return ResponseEntity.ok(icqFriendService.isFollow(userId,friendId));
    }

}
