package com.example.et;


import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.example.et.Ustlis.SimulatorUtils;
import com.example.et.Ustlis.Utils;
import com.example.et.Ustlis.internationalization.LocalManageUtil;
import com.example.et.util.CacheUtils;
import com.example.et.util.constant.CacheConstants;



public class HJZApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        if (!SimulatorUtils.isSimulator(this)) {
            Utils.init(this);//工具包相关初始化


            LocalManageUtil.setApplicationLanguage(this);
            String language = LocalManageUtil.getSelectLanguage(this);
            if (language.equals("ENGLISH")) {//英语  @"en"
                CacheUtils.getInstance().put(CacheConstants.Lang, "en");
            } else if (language.equals("Simplified Chinese")) {
                CacheUtils.getInstance().put(CacheConstants.Lang, "zh-Hans");
            } else if (language.equals("繁體中文")) {
                CacheUtils.getInstance().put(CacheConstants.Lang, "zh-Hant");
            }

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
