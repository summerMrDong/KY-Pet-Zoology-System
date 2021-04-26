package com.kaiyu.service;

import com.kaiyu.domain.entity.MallClassifyEntity;
import com.kaiyu.dto.Info;
import com.kaiyu.mapper.MallClassifyMapper;
import com.kaiyu.utils.BeanHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname MallClassifyService
 * @Description TODO
 * @Date 2021/4/14 0014 下午 3:02
 * @Created by 董乙辰
 */
@Service
public class MallClassifyService {

    private MallClassifyMapper mallClassifyMapper;

    @Autowired
    public MallClassifyService(MallClassifyMapper mallClassifyMapper) {
        this.mallClassifyMapper = mallClassifyMapper;
    }

    /**
     * 查询全部分类
     *
     * @return
     */
    public Info<List<MallClassifyEntity>> selClassifyAll() {
        List<MallClassifyEntity> entities = parseClassify(mallClassifyMapper.selectAll(), 0);
        return Info.ok(entities);
    }

    /**
     * 解析多级分类
     *
     * @param classifyEntities
     * @param parentId
     * @return
     */
    private List<MallClassifyEntity> parseClassify(List<MallClassifyEntity> classifyEntities, long parentId) {
        List<MallClassifyEntity> entities = classifyEntities.parallelStream().filter((a) -> a.getParentId().equals(parentId)).collect(Collectors.toList());

        if (entities.isEmpty()) {
            return entities;
        }

        classifyEntities.removeAll(entities);

        entities.forEach((a) -> a.setSubClassify(parseClassify(classifyEntities, a.getId())));

        return entities;

    }

    /**
     * 根据id查询分类
     *
     * @param id        分类id
     * @param isLinkage 是否联动（默认联动） 联动：将id下的子分类也取出来
     * @return
     */
    public Info<?> selClassifyById(Long id, Boolean isLinkage) {
        MallClassifyEntity entity = mallClassifyMapper.selectByPrimaryKey(id);

        if (isLinkage) {
            List<MallClassifyEntity> entities = parseClassify(mallClassifyMapper.selectAll(), id);

            entity.setSubClassify(entities);
        }

        return Info.ok(entity);
    }

}
