package com.utils;

import java.util.UUID;

/**
 * 生成随机字符串工具类
 * Created by yuanjie.fang on 2017/7/5.
 */
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
