package com.kaiyu.conf;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kaiyu.dto.Info;
import com.kaiyu.enums.ResponseEnum;
import com.kaiyu.pojo.UserInfo;
import com.kaiyu.utils.DateTimeFormat;
import com.kaiyu.utils.JWTUtils;
import com.netflix.zuul.context.RequestContext;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

/**
 * @Classname UserLoginController
 * @Description 登录成功后设置token token时间为2小时
 * @Date 2021/3/3 0003 下午 3:24
 * @Created by 董乙辰
 */
@Slf4j
@Setter
@Component
@ConfigurationProperties(prefix = "ky.token")
public class AuthPostFilter extends AuthFilter {

    private Integer inDate;
    private Float loadFactor = 0.5f;
    private static final String LOGIN_URI = "/api/user/login";

    @Override
    public boolean shouldFilter() {
        init();

        RequestContext requestContext = threadLocalContent.get();
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();
        boolean off = LOGIN_URI.equals(requestURI);

        if (!off && !allowPaths.contains(requestURI)) {
            JSONObject token = threadLocalUserInfo.get();

            if (Objects.nonNull(token)) {

                if (Objects.nonNull(token.getInteger(JWTUtils.EXP))) {
                    Integer inDate = token.getInteger(JWTUtils.IN_DATE);
                    long now = System.currentTimeMillis();
                    DateTime renewal = new DateTime((token.getInteger(JWTUtils.IAT) + (int) (inDate * (loadFactor > 1 ? 1 : loadFactor))) * 1000L);
                    log.info("续约时间：" + renewal.toString(DateTimeFormat.CN_TIME));
                    log.info("现在时间：" + new DateTime(now).toString(DateTimeFormat.CN_TIME));

                    if (renewal.isBefore(now)) {
                        String encrypt = JWTUtils.encrypt(token.get(JWTUtils.PAYLOAD), inDate);
                        requestContext.addZuulResponseHeader(HEADER_NAME, encrypt);
                        log.info("续约成功");
                    }

                }
            }

            removeTread();
        }

        return off;
    }

    @Override
    public Object run() {
        RequestContext requestContext = threadLocalContent.get();

        try {
            int code = requestContext.getResponseStatusCode();

            if (code == 200) {
                InputStream dataStream = requestContext.getResponseDataStream();
                String body = StreamUtils.copyToString(dataStream, StandardCharsets.UTF_8);
                JSONObject info = JSON.parseObject(body);
                int status = info.getIntValue("status");

                if (status == 200) {
                    UserInfo userInfo = JSON.parseObject(info.getString("data"), UserInfo.class);
                    String encrypt = Objects.isNull(inDate) ? JWTUtils.encrypt(userInfo) : JWTUtils.encrypt(userInfo, inDate);

                    requestContext.getResponse().setHeader(HEADER_NAME, encrypt);
                    requestContext.setResponseBody(body);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody(Info.ok(ResponseEnum.SERVER_UNKNOWN_ERROR).toString());
        }

        removeTread();
        return null;
    }

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }


    private void removeTread() {
        threadLocalContent.remove();
        threadLocalUserInfo.remove();
    }

}
