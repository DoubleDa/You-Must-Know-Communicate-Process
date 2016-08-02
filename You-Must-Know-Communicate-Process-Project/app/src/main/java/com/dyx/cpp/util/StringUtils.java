package com.dyx.cpp.util;

/**
 * project name：You-Must-Know-Communicate-Process-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/2 下午10:54
 * alter person：dayongxin
 * alter time：16/8/2 下午10:54
 * alter remark：
 */
public class StringUtils {
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }
}
