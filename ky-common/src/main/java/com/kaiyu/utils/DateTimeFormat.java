package com.kaiyu.utils;

import org.joda.time.DateTime;

/**
 * @Classname DateTimeFormat
 * @Description 时间格式化
 * @Date 2021/2/23 0023 下午 4:25
 * @Created by 董乙辰
 */
public class DateTimeFormat {
    public static final String CN_TIME = "yyyy-MM-dd HH:mm:ss";

    public static String cnTime() {
        return DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }

}
