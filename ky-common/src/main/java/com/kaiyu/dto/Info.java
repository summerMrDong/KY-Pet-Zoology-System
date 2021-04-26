package com.kaiyu.dto;

import com.alibaba.fastjson.JSON;
import com.kaiyu.enums.ResponseEnum;
import com.kaiyu.utils.JSONContent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

/**
 * @Classname Info
 * @Description 正常响应信息
 * @Date 2021/2/3 0003 下午 5:05
 * @Created by 董乙辰
 */
@Data
@NoArgsConstructor
public class Info<T> {

    /**
     * 状态码
     */
    private int status = 200;

    /**
     * 时间戳
     */
    private String timestamp = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");

    /**
     * 响应信息
     */
    private T data;

    public Info(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public Info(T data) {
        this.data = data;
    }

    public static <K> Info<K> ok(K body) {
        return new Info<>(body);
    }

    public static Info<String> ok() {
        return new Info<>("OK");
    }

    public static <K> Info<K> ok(int status, K body) {
        return new Info<>(status, body);
    }

    public static Info<String> ok(ResponseEnum body) {
        return new Info<>(body.getStatus(), body.getMessage());
    }

    @Override
    public String toString() {
        return JSON.toJSON(this).toString();
    }

}