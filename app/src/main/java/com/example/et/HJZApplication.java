package com.example.et;

import android.app.Application;
import android.content.Context;

import com.example.et.Ustlis.Utils;

public class HJZApplication extends Application {
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Utils.init(context);//工具包相关初始化
    }
}
