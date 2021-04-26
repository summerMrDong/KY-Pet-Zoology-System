package com.kaiyu.function;

import lombok.Getter;

@Getter
public enum FileType {

    /**
     * 图片
     */
    IMAGE(0),

    /**
     * 音频
     */
    VOICE(1),

    /**
     * 视频
     */
    VIDEO(2),

    /**
     * 文本
     */
    TEXT(3);

    public int code;

    FileType(int code) {
        this.code = code;
    }
}