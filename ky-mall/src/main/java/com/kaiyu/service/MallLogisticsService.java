package com.kaiyu.service;

import com.kaiyu.mapper.MallLogisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname MallFreightService
 * @Description TODO
 * @Date 2021/4/14 0014 下午 3:22
 * @Created by 董乙辰
 */
@Service
public class MallLogisticsService {
    private MallLogisticsMapper mallLogisticsMapper;

    @Autowired
    public MallLogisticsService(MallLogisticsMapper mallLogisticsMapper) {
        this.mallLogisticsMapper = mallLogisticsMapper;
    }
}
