package com.example.et.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.et.R;
import com.example.et.Ustlis.GifListener;
import com.example.et.Ustlis.ImageLoaderUtil;
import com.example.et.Ustlis.StatusBarUtils;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.constant.CacheConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 启动页
 */
public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.activity_view)
    ImageView activityView;   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //满屏显示
        //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
//        Glide.with(SplashActivity.this)
//                .load(R.drawable.et)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(activityView);
        //  BarUtils.setStatusBarVisibility(this,false);
        ImageLoaderUtil.loadOneTimeGif(this, R.drawable.et, activityView, new GifListener() {
            @Override
            public void gifPlayComplete() {
                Intent intent = new Intent();
                if (!CacheUtils.getInstance().getString("token").equals("")) {
                    intent.setClass(SplashActivity.this, MainActivity.class);
                } else {
                    intent.setClass(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });
        StatusBarUtils.with(this).init();
        //   handler.sendEmptyMessageDelayed(1, 3000);


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
