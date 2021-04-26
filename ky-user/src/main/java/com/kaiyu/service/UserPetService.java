package com.kaiyu.service;

import com.alibaba.fastjson.JSONObject;
import com.kaiyu.dto.Info;
import com.kaiyu.enums.ResponseEnum;
import com.kaiyu.mapper.GpsMapper;
import com.kaiyu.mapper.PetMapper;
import com.kaiyu.mapper.WXUserMapper;
import com.kaiyu.pojo.dto.PetDTO;
import com.kaiyu.pojo.entity.GpsEntity;
import com.kaiyu.pojo.entity.PetEntity;
import com.kaiyu.pojo.entity.UserEntity;
import com.kaiyu.pojo.vo.PetBreedVO;
import com.kaiyu.pojo.vo.PetClassifyVO;
import com.kaiyu.mapper.PetTypeMapper;
import com.kaiyu.pojo.entity.PetTypeEntity;
import com.kaiyu.utils.BeanHelper;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.omg.CORBA.PERSIST_STORE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Classname UserPetInfoService
 * @Description TODO
 * @Date 2021/3/30 0030 下午 2:19
 * @Created by 董乙辰
 */
@Service
public class UserPetService {

    private PetTypeMapper petTypeMapper;
    private PetMapper petMapper;
    private GpsMapper gpsMapper;
    private WXUserMapper wxUserMapper;

    @Autowired
    public UserPetService(PetTypeMapper petTypeMapper, PetMapper petMapper, GpsMapper gpsMapper, WXUserMapper wxUserMapper) {
        this.petTypeMapper = petTypeMapper;
        this.petMapper = petMapper;
        this.gpsMapper = gpsMapper;
        this.wxUserMapper = wxUserMapper;
    }

    /**
     * 宠物全部分类信息
     *
     * @return
     */
    public Info<List<PetClassifyVO>> selClassifyAll() {
        PetTypeEntity entity = new PetTypeEntity();

        entity.setParentId(0L);

        List<PetTypeEntity> entities = petTypeMapper.select(entity);
        List<PetClassifyVO> vos = null;

        if (!entities.isEmpty()) {
            vos = BeanHelper.copyWithCollection(entities, PetClassifyVO.class);
        }

        return Info.ok(Optional.ofNullable(vos).orElse(new ArrayList<>()));
    }

    /**
     * 分类信息
     *
     * @param classifyId
     * @return
     */
    public Info<PetClassifyVO> selClassify(Long classifyId) {
        PetTypeEntity entity = petTypeMapper.selectByPrimaryKey(classifyId);
        PetClassifyVO vo = null;

        if (Objects.nonNull(entity)) {
            vo = BeanHelper.copyProperties(entity, PetClassifyVO.class);
        }

        return Info.ok(vo);
    }

    /**
     * 类型下的宠物品种信息
     *
     * @param classifyId
     * @return
     */
    public Info<List<PetBreedVO>> selBreeds(Long classifyId) {
        PetTypeEntity entity = new PetTypeEntity();

        entity.setParentId(classifyId);

        List<PetTypeEntity> entities = petTypeMapper.select(entity);

        List<PetBreedVO> vos = null;

        if (!entities.isEmpty()) {
            vos = BeanHelper.copyWithCollection(entities, PetBreedVO.class);
        }

        return Info.ok(Optional.ofNullable(vos).orElse(new ArrayList<>()));
    }

    /**
     * 品种信息
     *
     * @param breedId
     * @return
     */
    public Info<PetBreedVO> selBreed(Long breedId) {
        PetTypeEntity entity = petTypeMapper.selectByPrimaryKey(breedId);
        PetBreedVO vo = null;

        if (Objects.nonNull(entity)) {
            vo = BeanHelper.copyProperties(entity, PetBreedVO.class);
        }

        return Info.ok(vo);
    }

    /**
     * 添加宠物
     *
     * @param userId
     * @param petDTO
     * @return
     */
    @Transactional
    public Info<String> addPet(Long userId, PetDTO petDTO) {
        PetEntity entity = new PetEntity();
        entity.setUserId(userId);
        entity.setName(petDTO.getName());
        List<PetEntity> list = petMapper.select(entity);

        if (list.size() > 1) {
            return Info.ok(ResponseEnum.NAME_EXIST);
        }

        PetEntity petEntity = BeanHelper.copyProperties(petDTO, PetEntity.class);

        String info = configInfo(petEntity.getClassifyId(), petEntity.getBreedId());
        String[] split = info.split("\\|");

        petEntity.setClassify(split[0]);
        petEntity.setBreed(split[1]);
        petEntity.setCreateTime(new Date());
        petEntity.setUserId(userId);
        petMapper.insertSelective(petEntity);

        return Info.ok();
    }

    /**
     * 删除宠物
     *
     * @param petId
     * @return
     */
    public Info<String> delPet(Long petId) {
        petMapper.deleteByPrimaryKey(petId);
        return Info.ok();
    }

    /**
     * 查询用户全部宠物
     *
     * @param userId
     * @return
     */
    public Info<List<PetEntity>> selPets(Long userId) {
        PetEntity entity = new PetEntity();
        entity.setUserId(userId);
        return Info.ok(petMapper.select(entity));
    }

    /**
     * 查询宠物详细信息
     *
     * @param petId
     * @return
     */
    public Info<PetEntity> selPet(Long petId) {
        return Info.ok(petMapper.selectByPrimaryKey(petId));
    }

    /**
     * 更新宠物信息
     *
     * @param petDTO
     * @return
     */
    public Info<PetEntity> updPet(PetDTO petDTO) {
        PetEntity entity = BeanHelper.copyProperties(petDTO, PetEntity.class);

        String info = configInfo(petDTO.getClassifyId(), petDTO.getBreedId());
        String[] split = info.split("\\|");

        entity.setClassify(split[0]);
        entity.setBreed(split[1]);

        petMapper.updateByPrimaryKeySelective(entity);
        PetEntity pet = petMapper.selectByPrimaryKey(petDTO.getId());

        return Info.ok(pet);
    }

    /**
     * 宠物绑定硬件信息
     *
     * @param petId
     * @param gpsId
     * @return
     */
    public Info<String> bindingMac(Long petId, String gpsId) {
        PetEntity petEntity = petMapper.selectByPrimaryKey(petId);

        if (Objects.isNull(petEntity)) {
            return Info.ok(0, "宠物不存在");
        }

        petEntity.setGpsId(gpsId);

        petMapper.updateByPrimaryKeySelective(petEntity);

        return Info.ok();
    }

    /**
     * 查询宠物设置信息
     *
     * @param classifyId
     * @param breedId
     * @return
     */
    private String configInfo(Long classifyId, Long breedId) {
        PetClassifyVO classifyVO = selClassify(classifyId).getData();
        PetBreedVO breedVO = selBreed(breedId).getData();

        if (Objects.isNull(classifyVO) || Objects.isNull(breedVO)) {
            return "未定义|未定义";
        }

        return classifyVO.getClassify() + "|" + breedVO.getBreed();
    }

    /**
     * 宠物实时数据（经纬度，温度）
     *
     * @param petId
     * @return
     */
    public Info<?> selPetData(Long petId) {
        PetEntity petEntity = petMapper.selectByPrimaryKey(petId);

        if (Objects.isNull(petEntity)) {
            return Info.ok(0, "宠物不存在");
        }

        String gpsId = petEntity.getGpsId();
        GpsEntity gpsEntity = gpsMapper.selectByPrimaryKey(gpsId);

        Long userId = petEntity.getUserId();
        UserEntity userEntity = wxUserMapper.selectByPrimaryKey(userId);
        String location = userEntity.getLocation();

        JSONObject object = new JSONObject();
        object.put("userLocation",location);
        object.put("petData",gpsEntity);

        return Info.ok(object);
    }
}
