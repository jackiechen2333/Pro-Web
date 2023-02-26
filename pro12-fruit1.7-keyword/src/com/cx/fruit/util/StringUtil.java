package com.cx.fruit.util;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/26-18:45
 * @Version 2022.2 1.8
 */
public class StringUtil {
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
