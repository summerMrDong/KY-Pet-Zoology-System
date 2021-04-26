package com.kaiyu.service;

import com.alibaba.fastjson.JSON;
import com.kaiyu.conf.WXConfig;
import com.kaiyu.dto.Info;
import com.kaiyu.pojo.WXAuthSession;
import com.kaiyu.enums.ExceptionEnum;
import com.kaiyu.enums.ResponseEnum;
import com.kaiyu.error.KyException;
import com.kaiyu.mapper.WXSessionMapper;
import com.kaiyu.mapper.WXUserMapper;
import com.kaiyu.pojo.entity.UserEntity;
import com.kaiyu.pojo.entity.WXSessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @Classname UserLoginService
 * @Description TODO
 * @Date 2021/3/3 0003 下午 3:28
 * @Created by 董乙辰
 */
@Service
public class UserLoginService {

    private WXConfig wx_config;

    private WXUserMapper wxUserMapper;

    private RestTemplate restTemplate;

    private WXSessionMapper wxSessionMapper;

    @Autowired
    public UserLoginService(WXConfig wx_config, WXUserMapper wxUserMapper, WXSessionMapper wxSessionMapper, RestTemplate restTemplate) {
        this.wx_config = wx_config;
        this.restTemplate = restTemplate;
        this.wxUserMapper = wxUserMapper;
        this.wxSessionMapper = wxSessionMapper;
    }

    /**
     * 微信授权登录
     *
     * @param userInfo
     * @param code
     * @return
     */
    public Info<Object> login(UserEntity userInfo, String code) {

        WXAuthSession authSession = getSessionKey(code);

        if (Objects.isNull(authSession)) {
            throw new KyException(ExceptionEnum.SERVER_UNKNOWN_ERROR);
        }

        if (!isSuccess(authSession)) {
            return Info.ok(authSession.getErrcode(), authSession.getErrmsg());
        }

        String openid = authSession.getOpenid();
        String unionId = authSession.getUnionid();

        insertSession(authSession);

        UserEntity userEntity = wxUserMapper.selectByOpenId(openid);
        if (Objects.isNull(userEntity)) {
            userEntity = insertUserInfo(userInfo, openid, unionId);
        }

        if (!Optional.ofNullable(unionId).orElse("").equals(Optional.ofNullable(userEntity.getUnionId()).orElse(""))) {
            UserEntity entity = new UserEntity();

            entity.setUserId(userEntity.getUserId());
            entity.setUnionId(unionId);
            wxUserMapper.updateByPrimaryKeySelective(entity);
            userEntity.setUnionId(unionId);
        }

        Boolean status = Optional.ofNullable(userEntity.getStatus()).orElse(true);
        return Info.ok(status ? userEntity : ResponseEnum.USER_DISABLE);
    }


    /**
     * 获取session_key
     * test http://192.168.0.108:8200/user/test
     *
     * @param code
     * @return
     */
    private WXAuthSession getSessionKey(String code) {
        String wx_url = String.format(wx_config.getWx_url(), code);
        String json = restTemplate.getForObject(wx_url, String.class);

        return JSON.parseObject(json, WXAuthSession.class);
    }

    /**
     * 获取session是否成功
     *
     * @param session
     * @return
     */
    private boolean isSuccess(WXAuthSession session) {

        Integer errCode = session.getErrcode();

        //0	请求成功
        if (Objects.isNull(errCode) || 0 == errCode) {
            return true;
        }

        //40029	code 无效
        if (40029 == errCode) {
            throw new KyException(errCode, session.getErrmsg());
        }

        //45011	频率限制，每个用户每分钟100次
        if (45011 == errCode) {
            throw new KyException(errCode, session.getErrmsg());
        }

        //-1	系统繁忙，此时请开发者稍候再试
        return false;

    }

    /**
     * 保存sessionKey
     *
     * @param session
     */
    private void insertSession(WXAuthSession session) {

        String openid = session.getOpenid();

        WXSessionEntity sessionEntity = new WXSessionEntity();
        sessionEntity.setOpenid(openid);
        sessionEntity.setSessionKey(session.getSession_key());
        sessionEntity.setUnionId(session.getUnionid());

        WXSessionEntity wxSessionEntity = wxSessionMapper.selectByPrimaryKey(openid);
        if (Objects.isNull(wxSessionEntity)) {
            sessionEntity.setCreateTime(new Date());
            wxSessionMapper.insertSelective(sessionEntity);
        } else {
            wxSessionMapper.updateByPrimaryKeySelective(sessionEntity);
        }

    }

    /**
     * 保存用户信息
     *
     * @param userInfo
     * @param openid
     * @param unionId
     */
    private UserEntity insertUserInfo(UserEntity userInfo, String openid, String unionId) {
        userInfo.setOpenId(openid);
        userInfo.setUnionId(unionId);
        userInfo.setCreateTime(new Date());
        wxUserMapper.insertSelective(userInfo);
        return userInfo;
    }
}
