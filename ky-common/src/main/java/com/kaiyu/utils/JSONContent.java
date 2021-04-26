package com.kaiyu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Classname JSONContent
 * @Description TODO
 * @Date 2021/3/19 0019 下午 12:27
 * @Created by 董乙辰
 */
public class JSONContent {
    public static void content(Object object) throws JsonProcessingException {
        String s = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
        System.out.println("\n" + s);
    }
}
