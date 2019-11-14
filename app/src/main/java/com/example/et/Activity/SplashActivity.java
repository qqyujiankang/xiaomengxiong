package com.example.et.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

import com.example.et.R;
import com.example.et.Ustlis.StatusBarUtils;
import com.example.et.util.BarUtils;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * 启动页
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //满屏显示
        //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //  BarUtils.setStatusBarVisibility(this,false);
        StatusBarUtils.with(this)
                .init();
        handler.sendEmptyMessageDelayed(1, 6000);

    }


    /**
     * 定义handler
     */
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
               // JPushInterface.setAlias(SplashActivity.this,  sequence,  alias);

                LogUtils.i("登录========" + CacheUtils.getInstance().getString("token"));
                Intent intent = new Intent();
                if (!CacheUtils.getInstance().getString("token").equals("")) {
                    intent.setClass(SplashActivity.this, MainActivity.class);
                } else {
                    intent.setClass(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();

            }
            return false;
        }
    });

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return false;


    }
}
