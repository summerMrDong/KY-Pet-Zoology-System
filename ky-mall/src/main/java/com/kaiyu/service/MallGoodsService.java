package com.kaiyu.service;

import com.alibaba.fastjson.JSONPath;
import com.kaiyu.domain.entity.MallClassifyEntity;
import com.kaiyu.domain.entity.MallGoodsSkuEntity;
import com.kaiyu.domain.entity.MallGoodsSpuEntity;
import com.kaiyu.dto.Info;
import com.kaiyu.mapper.MallGoodsSkuMapper;
import com.kaiyu.mapper.MallGoodsSpuMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.support.RequestContext;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Classname MallGoodsService
 * @Description TODO
 * @Date 2021/4/14 0014 下午 2:36
 * @Created by 董乙辰
 */
@Service
public class MallGoodsService {

    private MallGoodsSpuMapper mallGoodsSpuMapper;
    private MallGoodsSkuMapper mallGoodsSkuMapper;
    private MallClassifyService mallClassifyService;

    @Autowired
    public MallGoodsService(MallGoodsSpuMapper mallGoodsSpuMapper, MallGoodsSkuMapper mallGoodsSkuMapper, MallClassifyService mallClassifyService) {
        this.mallGoodsSpuMapper = mallGoodsSpuMapper;
        this.mallGoodsSkuMapper = mallGoodsSkuMapper;
        this.mallClassifyService = mallClassifyService;
    }

    /**
     * 查询所有商品信息
     *
     * @param classifyId 店内分类id
     * @param desc       是否升序
     * @return
     */
    public Info<?> selGoodsAll(Long classifyId, boolean desc) {
        Example.Builder builder = Example.builder(MallGoodsSpuEntity.class);

        if (Objects.nonNull(classifyId)) {
            builder.where(
                    Sqls.custom().andEqualTo("cid", classifyId)
            );
        }

        List<MallClassifyEntity> classifyEntities = mallClassifyService.selClassifyAll().getData();
        List<MallGoodsSpuEntity> spuList = mallGoodsSpuMapper.selectByExample(builder.build());

        List<Long> spuIdS = spuList.stream().map(MallGoodsSpuEntity::getId).collect(Collectors.toList());

        Example build = Example.builder(MallGoodsSkuEntity.class).where(Sqls.custom().andIn("spuId", spuIdS)).build();
        List<MallGoodsSkuEntity> skuList = mallGoodsSkuMapper.selectByExample(build);

        Map<Long, List<MallGoodsSkuEntity>> map = skuList.stream().collect(Collectors.groupingBy(MallGoodsSkuEntity::getSpuId));

        spuList.forEach((spu) -> {
            List<MallGoodsSkuEntity> spu_skuList = map.get(spu.getId());
            spu_skuList.sort((a, b) -> b.getSort().compareTo(a.getSort()));
            spu.setSkuList(spu_skuList);

            MallClassifyEntity parse = parse(classifyEntities, spu.getCid());
            assert parse != null;
            parse.getGoods().add(spu);
        });

        if (desc) {
            spuList.sort(Comparator.comparing(MallGoodsSpuEntity::getSort));
        } else {
            spuList.sort((a, b) -> b.getSort().compareTo(a.getSort()));
        }

        return Info.ok(classifyEntities);
    }

    /**
     * 封装数据
     *
     * @return
     */
    private MallClassifyEntity parse(List<MallClassifyEntity> classifyEntities, long cid) {

        for (MallClassifyEntity entity : classifyEntities) {

            if (entity.getId()==(cid)) {
                return entity;
            }

            List<MallClassifyEntity> subClassify = entity.getSubClassify();

            if (!subClassify.isEmpty()) {

                MallClassifyEntity parse = parse(subClassify, cid);

                if (Objects.nonNull(parse)) {
                    return parse;
                }
            }
        }

        return null;
    }


    public ResponseEntity<Info<?>> selGoodsById() {
        return null;
    }

}
