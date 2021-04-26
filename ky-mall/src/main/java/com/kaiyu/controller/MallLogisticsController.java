package com.kaiyu.controller;

import com.kaiyu.service.MallLogisticsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname MallFreightController
 * @Description TODO
 * @Date 2021/4/14 0014 下午 3:21
 * @Created by 董乙辰
 */
@RestController
@RequestMapping("/freight")
public class MallLogisticsController {
    private MallLogisticsService mallLogisticsService;

}
