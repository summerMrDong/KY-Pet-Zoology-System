package com.kaiyu.conf;

import com.alibaba.fastjson.JSONObject;
import com.kaiyu.enums.ResponseEnum;
import com.kaiyu.error.BaseExceptionAdvice;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname UploadWebSecurityConfig
 * @Description TODO
 * @Date 2021/2/8 0008 上午 11:25
 * @Created by 董乙辰
 */
@Configuration
@EnableWebSecurity
public class UploadWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 登录成功
     */
    AuthenticationSuccessHandler successHandler = (request, response, authentication) -> {
        process(response, ResponseEnum.USER_LOGIN_SUCCEED);
    };

    /**
     * 登录失败
     */
    AuthenticationFailureHandler failureHandler = (request, response, exception) -> {
        process(response, ResponseEnum.USER_LOGIN_FAILURE);
    };

    /**
     * 拒绝访问
     */
    AccessDeniedHandler deniedHandler = (request, response, authException) -> {
        process(response, ResponseEnum.USER_ACCESS_DENIED);
    };

    /**
     * 登录过期或未登录
     */
    AuthenticationEntryPoint authenticationEntryPoint = (request, response, authException) -> {
        process(response, ResponseEnum.USER_LOGIN_EXPIRE);
    };

    /**
     * 退出登录
     */
    LogoutSuccessHandler logoutHandler = ((request, response, authentication) -> {
        try {
            process(response, ResponseEnum.USER_LOGOUT_SUCCEED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });

    /**
     * 加工响应信息
     *
     * @param response
     * @param responseEnum
     * @throws IOException
     */
    private void process(HttpServletResponse response, ResponseEnum responseEnum) throws IOException {
        response.setHeader("Content-Type", "application/json; charset=utf-8");
        JSONObject messages = BaseExceptionAdvice.createMessage(responseEnum.getStatus(), responseEnum.getMessage());
        response.getWriter().write(messages.toJSONString());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.requestCache().init(http);
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/file/**/*").permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        http.formLogin().successHandler(successHandler);
        http.formLogin().failureHandler(failureHandler);
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.exceptionHandling().accessDeniedHandler(deniedHandler);
        http.logout().logoutSuccessHandler(logoutHandler);
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/upload/login").permitAll()
                .and()
                .logout().permitAll();
    }

}
