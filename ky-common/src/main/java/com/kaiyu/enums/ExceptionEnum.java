package com.kaiyu.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    UPLOAD_FILE_ERROR(500, "文件上传失败"),
    UPLOAD_IMAGE_FORMAT_ERROR(400, "不支持的图片格式"),
    UPLOAD_FILE_INVALID_ERROR(400, "无效的文件"),
    UPLOAD_TYPE_NOT_SUPPORTED_ERROR(400, "不支持的文件类型"),
    TOKEN_INVALID_ERROR(400, "无效的token"),
    TOKEN_OVERDUE_ERROR(400, "过期的token"),
    SERVER_UNKNOWN_ERROR(500, "服务未知异常"),
    DOWNLOAD_INVALID_ERROR(400, "无效的文件链接"),
    USER_NOT_EXIST(400,"用户不存在"),
    DATA_TRANSFER_ERROR(500,"数据转换异常"),
    ;

    /**
     * 状态码
     */
    private int status;

    /**
     * 错误信息
     */
    private String message;

    ExceptionEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
