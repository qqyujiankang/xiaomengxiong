package com.example.et;


import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.example.et.Ustlis.SimulatorUtils;
import com.example.et.Ustlis.Utils;
import com.example.et.Ustlis.internationalization.LocalManageUtil;
import com.example.et.util.CacheUtils;
import com.example.et.util.SharedPreferencesHelper;
import com.example.et.util.constant.CacheConstants;
import com.tencent.bugly.crashreport.CrashReport;

import cn.jpush.android.api.JPushInterface;


public class HJZApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        if (!SimulatorUtils.isSimulator(this)) {
            Utils.init(this);//工具包相关初始化


            LocalManageUtil.setApplicationLanguage(this);
            SharedPreferencesHelper.init(context, "user");
            String language = LocalManageUtil.getSelectLanguage(this);

            if (language.equals("ENGLISH")) {//英语  @"en"
                CacheUtils.getInstance().put(CacheConstants.Lang, "en");
            } else if (language.equals("简体中文")) {
                CacheUtils.getInstance().put(CacheConstants.Lang, "zh-Hans");
            } else if (language.equals("繁體中文")) {
                CacheUtils.getInstance().put(CacheConstants.Lang, "zh-Hant");
            }
            JPushInterface.setDebugMode(true);
            JPushInterface.init(this);
            CrashReport.initCrashReport(getApplicationContext(), "ccdec69fa9", true);
            CrashReport.initCrashReport(getApplicationContext());

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
