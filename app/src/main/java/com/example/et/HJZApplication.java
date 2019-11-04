package com.example.et;


import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.example.et.Activity.SplashActivity;
import com.example.et.Ustlis.SimulatorUtils;
import com.example.et.Ustlis.Utils;
import com.example.et.Ustlis.internationalization.LocalManageUtil;


public class HJZApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        if (!SimulatorUtils.isSimulator(this)) {
            Utils.init(this);//工具包相关初始化
        }


    }


    @Override
    protected void attachBaseContext(Context base) {
        //保存系统选择语言
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(LocalManageUtil.setLocal(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //保存系统选择语言
        LocalManageUtil.onConfigurationChanged(getApplicationContext());
    }


}
