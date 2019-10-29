package com.example.et.util.realize;

import android.app.Activity;
import android.util.Base64;

import com.example.et.Ustlis.StringUtils;
import com.example.et.entnty.Pagebean;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.constant.KeyValueConstants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * 解析list 实体类 HashMap 的数据万能
 * Created by Administrator on 2017/11/30 0030.
 */

public class ParseUtils {

    private ParseUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * @param activity
     * @param success  服务器传过来的数据
     * @param type     所需的实体类
     * @param cache    缓存数据到本地
     */

    public static <T> Pagebean<Object> analysisListTypeDatasAndCount(Activity activity, String success, Class<T[]> type, boolean cache) {
        Pagebean<Object> pageBean = null;
        List<Object> objectList;
        HashMap<String, Object> hashMap;

        try {
            JSONObject jsonObject = new JSONObject(success);
            pageBean = new Pagebean<>();
            objectList = new ArrayList<>();
            hashMap = new HashMap<String, Object>();


            //{"msg":"地址查找成功!","code":"200","data":[{"id":1,"userName":"李涛","phoneNum":18258587474,"province":"山西省运城市","address":"中建大厦","isDefault":2,"modifyTime":1521706043000,"agencyNumber":"RY00000005"},
//                                            {"id":3,"userName":"杨先生","phoneNum":18434867118,"province":"山西江西","address":"运城","isDefault":1,"modifyTime":1521707133000,"agencyNumber":"RY00000005"}]}

            //                                       {"count":4,"list":[{"posTypeName":"MPOS","machineNumber":"126498416519649816494","cellphone":"18192591109","customName":"你好","customId":"61654841"},{"posTypeName":"DPOS","machineNumber":"855555555555555","cellphone":"15535958281","customName":"庾建康","customId":""},{"posTypeName":"DPOS","machineNumber":"65555874858","cellphone":"15535958281","customName":"庾建康","customId":""},{"posTypeName":"DPOS","cellphone":"18192591109","customName":"牛点蒂汉字汉字还","customId":""}]}

            Iterator<String> iteratorAll = jsonObject.keys();

            while (iteratorAll.hasNext()) {
                String paramsKeyAll = iteratorAll.next();
                if (paramsKeyAll.equals(KeyValueConstants.DATA) && !StringUtils.isEmpty(jsonObject.getString(KeyValueConstants.DATA))) {
                    //String dataJson = new String(Base64.decode(jsonObject.getString(KeyValueConstants.DATA).getBytes(), Base64.DEFAULT));
                    String dataJson = new String(jsonObject.getString(KeyValueConstants.DATA).getBytes());
                    //  LogUtils.i(activity.getClass() + "解析之后的数据--------" + dataJson);
                    if (dataJson.startsWith("[") && dataJson.endsWith("]")) {
                        Collections.addAll(objectList, new Gson().fromJson(dataJson, type));
                    } else {
                        JSONObject dataJsonObject = new JSONObject(dataJson);
                        Iterator<String> iterator = dataJsonObject.keys();
                        while (iterator.hasNext()) {
                            String paramsKey = iterator.next();
                            if (paramsKey.equals(KeyValueConstants.LIST) && !StringUtils.isEmpty(dataJsonObject.getString(KeyValueConstants.LIST))) {
                                Collections.addAll(objectList, new Gson().fromJson(dataJsonObject.getString(KeyValueConstants.LIST), type));
                            } else {
                                if (cache) {
                                    CacheUtils.getInstance().put(paramsKey, dataJsonObject.get(paramsKey) + "");
                                } else {
                                    hashMap.put(paramsKey, dataJsonObject.get(paramsKey));
                                }

                            }
                        }
                    }

                } else {
                    hashMap.put(paramsKeyAll, jsonObject.get(paramsKeyAll) + "");
                }
            }

            pageBean.setStringMap(hashMap);
            pageBean.setList(objectList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageBean;
    }

    public static <T> Pagebean<Object> analysisListTypeDatasAndCount1(Activity activity, String success, Class<T[]> type, boolean cache) {
        Pagebean<Object> pageBean = null;
        List<Object> objectList;
        HashMap<String, Object> hashMap;

        try {
            JSONObject jsonObject = new JSONObject(success);
            pageBean = new Pagebean<>();
            objectList = new ArrayList<>();
            hashMap = new HashMap<String, Object>();


            //{"msg":"地址查找成功!","code":"200","data":[{"id":1,"userName":"李涛","phoneNum":18258587474,"province":"山西省运城市","address":"中建大厦","isDefault":2,"modifyTime":1521706043000,"agencyNumber":"RY00000005"},
//                                            {"id":3,"userName":"杨先生","phoneNum":18434867118,"province":"山西江西","address":"运城","isDefault":1,"modifyTime":1521707133000,"agencyNumber":"RY00000005"}]}

            //                                       {"count":4,"list":[{"posTypeName":"MPOS","machineNumber":"126498416519649816494","cellphone":"18192591109","customName":"你好","customId":"61654841"},{"posTypeName":"DPOS","machineNumber":"855555555555555","cellphone":"15535958281","customName":"庾建康","customId":""},{"posTypeName":"DPOS","machineNumber":"65555874858","cellphone":"15535958281","customName":"庾建康","customId":""},{"posTypeName":"DPOS","cellphone":"18192591109","customName":"牛点蒂汉字汉字还","customId":""}]}

            Iterator<String> iteratorAll = jsonObject.keys();

            while (iteratorAll.hasNext()) {
                String paramsKeyAll = iteratorAll.next();
                if (paramsKeyAll.equals(KeyValueConstants.DATA) && !StringUtils.isEmpty(jsonObject.getString(KeyValueConstants.DATA))) {
                    String dataJson1 = new String(jsonObject.getString(KeyValueConstants.DATA).getBytes());
                    JSONObject jsonObject1 = new JSONObject(dataJson1);
                    String ass = new String(jsonObject1.getString(KeyValueConstants.ass).getBytes());


                    if (ass.startsWith("[") && ass.endsWith("]")) {
                        Collections.addAll(objectList, new Gson().fromJson(ass, type));
                    } else {
                        JSONObject dataJsonObject = new JSONObject(ass);
                        Iterator<String> iterator = dataJsonObject.keys();
                        while (iterator.hasNext()) {
                            String paramsKey = iterator.next();
                            if (paramsKey.equals(KeyValueConstants.LIST) && !StringUtils.isEmpty(dataJsonObject.getString(KeyValueConstants.LIST))) {
                                Collections.addAll(objectList, new Gson().fromJson(dataJsonObject.getString(KeyValueConstants.LIST), type));
                            } else {
                                if (cache) {
                                    CacheUtils.getInstance().put(paramsKey, dataJsonObject.get(paramsKey) + "");
                                } else {
                                    hashMap.put(paramsKey, dataJsonObject.get(paramsKey));
                                }

                            }
                        }
                    }

                } else {
                    hashMap.put(paramsKeyAll, jsonObject.get(paramsKeyAll) + "");
                }
            }

            pageBean.setStringMap(hashMap);
            pageBean.setList(objectList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageBean;
    }


}
