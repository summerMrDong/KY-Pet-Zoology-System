package com.kaiyu.conf;

import com.alibaba.fastjson.JSONObject;
import com.kaiyu.pojo.UserInfo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Classname AuthFilter
 * @Description TODO
 * @Date 2021/3/20 0020 下午 1:53
 * @Created by 董乙辰
 */
public abstract class AuthFilter extends ZuulFilter {

    protected static ThreadLocal<RequestContext> threadLocalContent = new ThreadLocal<>();
    protected static ThreadLocal<JSONObject> threadLocalUserInfo = new ThreadLocal<>();
    protected static List<String> allowPaths;

    protected static final String HEADER_NAME = "kyToken";

    protected void init() {
        threadLocalContent.set(RequestContext.getCurrentContext());
    }
}
