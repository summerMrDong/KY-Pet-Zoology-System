package com.kaiyu.controller;

import com.kaiyu.service.MallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname MallOrderController
 * @Description TODO
 * @Date 2021/4/14 0014 下午 3:25
 * @Created by 董乙辰
 */
@RestController
@RequestMapping("/order")
public class MallOrderController {
    private MallOrderService mallOrderService;

    @Autowired
    public MallOrderController(MallOrderService mallOrderService) {
        this.mallOrderService = mallOrderService;
    }
}
