package com.kaiyu.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Classname ResponseEnum
 * @Description 响应的提示信息
 * @Date 2021/2/4 0004 下午 1:55
 * @Created by 董乙辰
 */
@Getter
public enum ResponseEnum {
    UPLOAD_FILE_SUCCEED(200, "文件上传成功"),
    USER_LOGIN_SUCCEED(200, "登录成功"),
    USER_LOGIN_FAILURE(400, "用户名或密码错误"),
    USER_ACCESS_DENIED(400, "拒绝访问"),
    USER_LOGOUT_SUCCEED(200, "退出成功"),
    USER_DISABLE(0, "用户停用"),
    USER_LOGIN_EXPIRE(400, "未登录或过期"),
    USER_NOT_CARRY_TOKEN(400, "请携带token"),
    USER_EXIST(400, "用户已存在"),
    USER_LINE(100, "用户离线状态"),
    USER_EXIST_FRIEND(1, "已关注"),
    SERVER_UNKNOWN_ERROR(500, "服务未知异常"),
    NAME_EXIST(0, "名称已存在"),
    OK(200, "成功"),
    ;
    /**
     * 状态码
     */
    private int status;

    /**
     * 消息
     */
    private String message;

    ResponseEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
