package com.example.et.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/1/22 0022.
 */

public class JsonUtil {


    // {"userId":"2c9281b4636cbda201636cc4b9a00001","machineId":"00000402G21000U8000"}


    public static HashMap parseJSON(String strJSON) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject dataJsonObject = new JSONObject(strJSON);
            Iterator<String> iterator = dataJsonObject.keys();
            while (iterator.hasNext()) {
                String paramsKey = iterator.next();
                hashMap.put(paramsKey, dataJsonObject.get(paramsKey));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hashMap;

    }

    /**
     * 判断字符串是否为json字符串
     *
     * @param str 字符串
     * @return true or false
     */
    public static boolean isGoodJson(String str) {
        if (TextUtils.isEmpty(str))
            return false;
        try {

            new JsonParser().parse(str);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }

    /**
     * 判断是否包含error code
     *
     * @param str 返回的字符串
     * @return true or false
     */
    public static boolean isErrorCode(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (str.startsWith("[") && str.endsWith("]")) {
            return false;
        }
        boolean isErrorCode = false;
        try {
            JSONObject jsonObject = new JSONObject(str);
            Iterator<?> keyIterator = jsonObject.keys();
            String key;
            while (keyIterator.hasNext()) {
                key = (String) keyIterator.next();
                if (key.equals(OkHttpUtils.errorCode)) {
                    isErrorCode = true;
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return isErrorCode;
    }


    /**
     * 将json字符串转化成实体对象
     *
     * @param json
     * @param classOfT
     * @return
     */

    public static Object stringToObject(String json, Class classOfT) {
        return new Gson().fromJson(json, classOfT);
    }


    /**
     * 将对象准换为json字符串 或者 把list 转化成json
     *
     * @param object
     * @param <T>
     * @return
     */

    public static <T> String objectToString(T object) {
        return new Gson().toJson(object);
    }


    /**
     * 把json 字符串转化成list
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */

    public static <T> List<T> stringToList(String json, Class<T> cls) {

        Gson gson = new Gson();

        List<T> list = new ArrayList<T>();

        JsonArray array = new JsonParser().parse(json).getAsJsonArray();

        for (final JsonElement elem : array) {

            list.add(gson.fromJson(elem, cls));

        }

        return list;

    }

    public static <E> Iterator<E> getIteratorE(String json) {
        if (TextUtils.isEmpty(json))
            return null;
        try {
            JsonElement jsonElement = new JsonParser().parse(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Set<E> stringSet = (Set<E>) jsonObject.keySet();
            Iterator<E> it = stringSet.iterator();
            return it;
        } catch (JsonParseException e) {
            return null;
        }
    }
}
