package com.example.et.util.model;


import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;

import org.json.JSONObject;

import java.util.Map;


/**
 * Created by Administrator on 2018/1/22 0022.
 */

public interface TaskLoadModel {

    void loadGet(String url, Map<String, Object> params, OnLoadListener<String> listener);
    void loadGet(String url, Map<String, Object> params, OnLoadLifefulListener<String> listener);

    void loadPost(String url, Map<String, Object> params, OnLoadLifefulListener<String> listener);
    void loadPost(String url, JSONObject jsonObject, OnLoadLifefulListener<String> listener);
    void loadPost(String url, Map<String, Object> params, OnLoadListener<String> listener);
}
