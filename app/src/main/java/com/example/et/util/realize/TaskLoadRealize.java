package com.example.et.util.realize;


import com.example.et.Activity.LoginActivity;
import com.example.et.Activity.MainActivity;
import com.example.et.HJZApplication;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.GsonUtil;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.entnty.Netstat;
import com.example.et.util.LogUtils;
import com.example.et.util.OkHttpUtils;
import com.example.et.util.callback.OkCallback;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.model.TaskLoadModel;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/22 0022.
 */

public class TaskLoadRealize implements TaskLoadModel {


    /**
     * 不可以上传文件
     *
     * @param params 中Object不可以是file
     */
    @Override
    public void loadGet(String url, Map<String, Object> params, final OnLoadListener<String> listener) {

        OkHttpUtils.get(url, params, new OkCallback() {
            @Override
            public void onResponse(String response) {
                if (listener != null) {

                    listener.onSuccess(response);
                }
            }

            @Override
            public void onFailure(String error) {
                if (listener != null) {
                    listener.onError(error);
                }
            }
        });
    }

    /**
     * 不可以上传文件
     *
     * @param params 中Object不可以是file
     */
    @Override
    public void loadGet(String url, Map<String, Object> params, OnLoadLifefulListener<String> listener) {

    }

    /**
     * 可以上传文件
     */
    @Override
    public void loadPost(String url, Map<String, Object> params, final OnLoadLifefulListener<String> listener) {

        OkHttpUtils.post(url, params, new OkCallback() {
            @Override
            public void onResponse(String response) {
                if (listener != null) {

                    listener.onSuccess(response);
                }
            }

            @Override
            public void onFailure(String error) {
                LogUtils.i(error);
                if (listener != null) {
                    listener.onError(error);
                }
            }
        });
    }

    /**
     * 可以上传文件
     */
    @Override
    public void loadPost(String url, JSONObject jsonObject, final OnLoadLifefulListener<String> listener) {

        OkHttpUtils.post(url, jsonObject, new OkCallback() {
            @Override
            public void onResponse(String response) {
                Netstat netstat = GsonUtil.GsonToBean(response, Netstat.class);
                if (netstat.getCode() != 500) {
                    if (listener != null) {
                        listener.onSuccess(response);
                    }
                } else {
                    //ToastUtils.showShort(netstat.getMsg().toString());

                    if (!ActivityUtils.isActivityExistsInStack(LoginActivity.class)) {
                        ActivityUtils.finishAllActivities();
                        ActivityUtils.startActivity(LoginActivity.class);
                    }


                }
            }

            @Override
            public void onFailure(String error) {
//                ActivityUtils.finishActivity(MainActivity.class);
//                ActivityUtils.startActivity(LoginActivity.class);
                if (listener != null) {
                    listener.onError(error);
                }
            }
        });
    }

    /**
     * 可以上传文件
     *
     * @param params
     */
    @Override
    public void loadPost(String url, Map<String, Object> params, OnLoadListener<String> listener) {

    }
}
