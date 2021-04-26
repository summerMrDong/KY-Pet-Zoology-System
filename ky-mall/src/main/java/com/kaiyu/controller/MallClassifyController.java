package com.kaiyu.controller;

import com.kaiyu.domain.entity.MallClassifyEntity;
import com.kaiyu.dto.Info;
import com.kaiyu.service.MallClassifyService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname MallClassifyController
 * @Description TODO
 * @Date 2021/4/14 0014 下午 2:40
 * @Created by 董乙辰
 */
@RestController
@RequestMapping("/classify")
public class MallClassifyController {

    private MallClassifyService mallClassifyService;

    @Autowired
    public MallClassifyController(MallClassifyService mallClassifyService) {
        this.mallClassifyService = mallClassifyService;
    }

    /**
     * 查询全部分类
     *
     * @return
     */
    @GetMapping("/sel/all")
    public ResponseEntity<Info<?>> selClassifyAll() {
        Info<List<MallClassifyEntity>> info = mallClassifyService.selClassifyAll();
        return ResponseEntity.ok(info);
    }

    /**
     * 根据id查询分类
     * @param id 分类id
     * @param isLinkage 是否联动（默认联动） 联动：将id下的子分类也取出来
     * @return
     */
    @GetMapping("/sel")
    public ResponseEntity<Info<?>> selClassifyById(
            @RequestParam Long id,
            @RequestParam(defaultValue = "true") Boolean isLinkage
    ) {
        Info<?> info = mallClassifyService.selClassifyById(id, isLinkage);
        return ResponseEntity.ok(info);
    }

}
