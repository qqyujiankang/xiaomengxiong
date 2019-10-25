package com.example.et.Activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.util.lifeful.Lifeful;


public class BaseActivity extends AppCompatActivity implements Lifeful, ComponentCallbacks2 {
    private Activity activity;
    private int guideResourceId;
    private boolean show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        //   permissions( );
        // permissions( );
        // android 7.0系统解决拍照的问题exposed beyond app through ClipData.Item.getUri()
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();


    }

    @Override
    protected void onStart() {
        super.onStart();
        //addGuideImage();// 添加引导页
    }

    /**
     * 页面 初始化布局
     */
    public void initView() {

    }

    ;

    public void getIntentDatas() {
    }

    /**
     * 请求数据
     */
    public void requestDatas() {


    }


    public void requestDatas2() {

    }


    /**
     * 检测数据是否满足条件
     */
    public boolean verificationDatas() {

        return false;
    }

    @Override
    public boolean isAlive() {
        if (activity == null)
            return false;
        return !(activity.isDestroyed() || activity.isFinishing());
    }


    /**
     * 打开 APP 的详情设置
     */
    AlertDialog builder;

    protected void openAppDetails(String message) {

        if (builder != null && builder.isShowing()) {
            builder.dismiss();
            builder = null;
        }
        builder = new AlertDialog.Builder(activity).create();

        View view = activity.getLayoutInflater().inflate(R.layout.dialog_permission, null);
        TextView permission_message = view.findViewById(R.id.permission_message);
        TextView permission_message1 = view.findViewById(R.id.permission_message1);
        permission_message.setText(message);
        permission_message1.setText(Html.fromHtml(getString(R.string.permission_text_a3)));

        TextView permission_cancel = view.findViewById(R.id.permission_cancel);
        TextView permission_ok = view.findViewById(R.id.permission_ok);

        builder.setCancelable(false);
        builder.setView(view);
        builder.show();
        permission_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + getPackageName()));
                ActivityUtils.startActivity(intent);
                builder.dismiss();


            }
        });
        permission_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong(R.string.forget_the_password);
                builder.dismiss();
            }
        });
    }

}
