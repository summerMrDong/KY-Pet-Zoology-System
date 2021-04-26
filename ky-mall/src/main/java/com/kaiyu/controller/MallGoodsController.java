package com.kaiyu.controller;

import com.kaiyu.dto.Info;
import com.kaiyu.service.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Classname MallGoodsController
 * @Description TODO
 * @Date 2021/4/14 0014 下午 2:08
 * @Created by 董乙辰
 */
@RestController
@RequestMapping("/goods")
public class MallGoodsController {

    private MallGoodsService mallGoodsService;

    @Autowired
    public MallGoodsController(MallGoodsService mallGoodsService) {
        this.mallGoodsService = mallGoodsService;
    }

    /**
     * 查询所有商品信息
     *
     * @param classifyId 店内分类id
     * @param desc       是否升序
     * @return
     */
    @GetMapping("/sel/all")
    public ResponseEntity<Info<?>> selGoodsAll(Long classifyId, boolean desc) {
        Info<?> info = mallGoodsService.selGoodsAll(classifyId, desc);
        return ResponseEntity.ok(info);
    }

    /**
     * 根据id查询商品信息
     *
     * @return
     */
    @GetMapping("/sel")
    public ResponseEntity<Info<?>> selGoodsById() {
        mallGoodsService.selGoodsById();
        return null;
    }

}
