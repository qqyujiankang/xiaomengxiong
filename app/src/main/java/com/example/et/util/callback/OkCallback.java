package com.example.et.util.callback;

/**
 * Created by Administrator on 2018/1/22 0022.
 */

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.et.util.CacheUtils;
import com.example.et.util.JsonUtil;
import com.example.et.util.LogUtils;
import com.example.et.util.constant.CacheConstants;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Smile Wei
 * @since 2016/8/2.
 */
public abstract class OkCallback implements Callback {
    private static Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailure("");
            }
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
         // Log.i("exp_","=bbbb======"+response.headers());
        // TODO: 2018/3/23 后期需要修改
        if (response.headers().get("Set-Cookie")!=null) {//储存cookie
            String cookie = response.headers().get("Set-Cookie");
            cookie = cookie.substring(0, cookie.indexOf(";"));
            LogUtils.i("cookie========="+cookie);
            //CacheUtils.getInstance().put(CacheConstants.TOKEN,cookie);
        }

        ResponseBody body = response.body();

        if (body == null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure("");
                }
            });
            return;
        }
        final String res = body.string();

        if (!JsonUtil.isGoodJson(res)) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure("");
                }
            });
            return;
        }

        if (JsonUtil.isErrorCode(res)) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(res);
                }
            });
            return;
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                onResponse(res);
            }
        });
    }

    public abstract void onResponse(String response);

    public abstract void onFailure(String error);

}
