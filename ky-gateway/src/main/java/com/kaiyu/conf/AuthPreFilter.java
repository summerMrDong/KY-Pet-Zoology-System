package com.kaiyu.conf;


import com.alibaba.fastjson.JSONObject;
import com.kaiyu.dto.Info;
import com.kaiyu.enums.ResponseEnum;
import com.kaiyu.error.KyBigException;
import com.kaiyu.utils.JWTUtils;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Slf4j
@Component
public class AuthPreFilter extends AuthFilter {

    @Autowired
    public AuthPreFilter(FilterConfig filterConfig) {
        allowPaths = filterConfig.getAllowPaths();
    }


    @Override
    public boolean shouldFilter() {
        init();

        HttpServletRequest request = threadLocalContent.get().getRequest();
        return !allowPaths.contains(request.getRequestURI());
    }

    @Override
    public Object run() {
        RequestContext requestContext = threadLocalContent.get();
        String ky_token = requestContext.getRequest().getHeader(HEADER_NAME);

        if (StringUtil.isEmpty(ky_token)) {
            String msg = Info.ok(ResponseEnum.USER_NOT_CARRY_TOKEN).toString();

            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody(msg);

            return null;
        }

        try {
            JSONObject token = new JSONObject(JWTUtils.decode(ky_token));
            threadLocalUserInfo.set(token);
        } catch (KyBigException e) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody(Info.ok(e.getStatus(), e.getMessage()).toString());
        }
        return null;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }

}
