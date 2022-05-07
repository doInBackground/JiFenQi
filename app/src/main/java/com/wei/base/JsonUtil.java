package com.wei.base;

import com.google.gson.Gson;

import java.util.List;

/**
 * json的操作类
 *
 * @author wsz
 * @version 1.0
 * @createTime 2012-4-12
 */
public class JsonUtil {

    private static Gson sGson;

    /**
     * 将java对象转换成json字符串
     *
     * @param bean
     * @return
     */
    public static String beanToJson(Object bean) {
        if (sGson == null) {
            sGson = new Gson();
        }
        return sGson.toJson(bean);
    }

    /**
     * 将java对象List集合转换成json字符串
     *
     * @param beans
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String beanListToJson(List beans) {
        StringBuffer rest = new StringBuffer();
        rest.append("[");
        int size = beans.size();
        for (int i = 0; i < size; i++) {
            rest.append(beanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));
        }
        rest.append("]");
        return rest.toString();
    }

}