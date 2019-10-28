package com.example.et;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.CountDownTimerUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.util.CacheUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.constant.KeyValueConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * 短信
 */
public class Verification {
    private Context context;
    private Lifeful lifeful;
    private String url;
    private TextView view;
    private int anInt;

    public Verification(Context context, Lifeful lifeful, String url, TextView textView, int i) {
        this.context = context;
        this.lifeful = lifeful;
        this.url = url;
        this.view = textView;
        this.anInt = i;
        redata();
    }

    private void redata() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));

            jsonObject.put("types", anInt);

            TaskPresenterUntils.lifeful(url, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, true).getStringMap();
                    if (resultMap.get(KeyValueConstants.CODE).equals("200")) {
                        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(view, 60000, 1000);
                        countDownTimerUtils.start();
                    }
                    ToastUtils.showShort(resultMap.get(KeyValueConstants.MSG).toString());
                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
