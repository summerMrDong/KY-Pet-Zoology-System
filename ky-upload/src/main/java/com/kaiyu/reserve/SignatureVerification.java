//package com.kaiyu.conf;
//
//import com.alibaba.fastjson.JSONObject;
//import com.kaiyu.controller.UploadController;
//import com.kaiyu.error.BaseExceptionAdvice;
//import com.kaiyu.utils.JWTUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import tk.mybatis.mapper.util.StringUtil;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class SignatureVerification implements HandlerInterceptor {
//    private boolean result;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        String token_name = request.getHeader(UploadController.token_name);
//        if (StringUtil.isNotEmpty(token_name)) {
//            if (JWTUtils.isPass(token_name)) {
//                return true;
//            }
//            result = true;
//        }
//        createResponseInfo(response);
//        return false;
//    }
//
//    /**
//     * 创建响应信息
//     *
//     * @param response
//     */
//    public void createResponseInfo(HttpServletResponse response) throws IOException {
//        response.setHeader("Content-Type", "application/json; charset=utf-8");
//        JSONObject message = BaseExceptionAdvice.createMessage(400, result ? "过期的token" : "请携带token");
//        response.getWriter().write(message.toJSONString());
//    }
//}