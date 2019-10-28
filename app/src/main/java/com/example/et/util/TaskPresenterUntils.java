package com.example.et.util;


import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.TaskLoadRealize;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * 子线程 执行相关
 */

public class TaskPresenterUntils {
    /**
     * @param url      请求地址 如：FileManager.getInstance().getRequestUrl(mContext, "requestGoodsSource")
     * @param params   上传参数 不可为空
     * @param listener 回调监听函数
     */
    public static void lifeful(String url, @NonNull Map<String, Object> params, OnLoadLifefulListener<String> listener) {
        new TaskLoadRealize().loadPost(url, params, listener);
    }


    public static void lifeful(String url, @NonNull JSONObject jsonObject, OnLoadLifefulListener<String> listener) {
        //LogUtils.i("app参数=========="+jsonObject+"=========url======"+url);
        new TaskLoadRealize().loadPost(url, jsonObject, listener);
    }





}
