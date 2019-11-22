package com.example.et.Ustlis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
public class JsonUtils {
    public static   <T> T StrToJson(String strJson, Class<T> classOfT)
    {
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            return gson.fromJson(strJson, classOfT);
        }
        catch (Exception e)
        {
            System.out.println("11111111111:"+e.getMessage());
            return null;
        }
    }


    /**
     * @param strJson json字符串
     */
    public static String StrToJson(Object strJson) {
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            return gson.toJson(strJson);
        } catch (Exception e) {
            System.out.println("11111111111:" + e.getMessage());
            return null;
        }
    }
}
