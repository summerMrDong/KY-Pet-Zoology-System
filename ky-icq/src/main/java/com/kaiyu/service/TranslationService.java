package com.kaiyu.service;

import com.kaiyu.dto.Info;
import com.kaiyu.mapper.PetDictionariesMapper;
import com.kaiyu.mapper.PetVoiceMapper;
import com.kaiyu.pojo.PetDictionariesEntity;
import com.kaiyu.pojo.PetVoiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Classname TranslationService
 * @Description TODO
 * @Date 2021/4/2 0002 下午 1:11
 * @Created by 董乙辰
 */
@Service
public class TranslationService {

    private PetVoiceMapper petVoiceMapper;
    private PetDictionariesMapper dictionariesMapper;

    @Autowired
    public TranslationService(PetVoiceMapper petVoiceMapper, PetDictionariesMapper dictionariesMapper) {
        this.petVoiceMapper = petVoiceMapper;
        this.dictionariesMapper = dictionariesMapper;
    }

    /**
     * 人宠互译
     *
     * @param classifyId 宠物分类
     * @return
     */
    public Info<?> personToPet(Long classifyId) {
        PetVoiceEntity entity = new PetVoiceEntity();
        entity.setClassifyId(classifyId);

        List<PetVoiceEntity> entities = petVoiceMapper.select(entity);

        if (entities.isEmpty()) {
            return Info.ok("没有可选音频");
        }

        int i = new Random().nextInt(entities.size());

        return Info.ok(entities.get(i));
    }

    /**
     * 宠人互译
     *
     * @return
     */
    public Info<?> petToPerson() {
        List<PetDictionariesEntity> entities = dictionariesMapper.selectAll();

        if (entities.isEmpty()) {
            return Info.ok("没有可选词汇");
        }

        int i = new Random().nextInt(entities.size());

        return Info.ok(entities.get(i));
    }
}
