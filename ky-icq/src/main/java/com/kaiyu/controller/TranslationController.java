package com.kaiyu.controller;

import com.kaiyu.dto.Info;
import com.kaiyu.pojo.PetDictionariesEntity;
import com.kaiyu.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TranslationController
 * @Description 互译
 * @Date 2021/4/2 0002 上午 9:57
 * @Created by 董乙辰
 */
@RestController
@RequestMapping("/translation")
public class TranslationController {

    private TranslationService translationService;

    @Autowired
    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    /**
     * 人宠互译
     *
     * @param classifyId 宠物分类
     * @return
     */
    @GetMapping("/to/pet/{classifyId}")
    public ResponseEntity<Info<?>> personToPet(@PathVariable Long classifyId) {
        return ResponseEntity.ok(translationService.personToPet(classifyId));
    }

    /**
     * 宠人互译
     * @return
     */
    @GetMapping("/to/person")
    public ResponseEntity<Info<?>> petToPerson() {
        return ResponseEntity.ok(translationService.petToPerson());
    }

}
