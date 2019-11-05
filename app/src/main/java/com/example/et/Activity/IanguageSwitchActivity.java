package com.example.et.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.HJZApplication;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.internationalization.LocalManageUtil;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.constant.CacheConstants;


import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 * 语言切换
 */
public class IanguageSwitchActivity extends BaseActivity {

    @BindView(R.id.public_back)
    TextView publicBack;
    @BindView(R.id.public_title_tv)
    TextView publicTitleTv;
    @BindView(R.id.public_other)
    TextView publicOther;
    @BindView(R.id.iv_public_other)
    ImageView ivPublicOther;
    @BindView(R.id.rl_bacground)
    RelativeLayout rlBacground;
    @BindView(R.id.Rl_)
    RelativeLayout Rl;
    @BindView(R.id.Rl_1)
    RelativeLayout Rl1;
    @BindView(R.id.Rl_2)
    RelativeLayout Rl2;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = IanguageSwitchActivity.this;
        setContentView(R.layout.activity_ianguage_switch);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(R.string.language_switch);
    }

    @OnClick({R.id.public_back, R.id.Rl_, R.id.Rl_1, R.id.Rl_2})
    public void onViewClicked(View view) {
        int selectedLanguage = 0;
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.Rl_:
                selectLanguage(1);
                break;
            case R.id.Rl_1:
                selectLanguage(2);
                break;
            case R.id.Rl_2:
                selectLanguage(3);
                break;
            default:
        }
    }


    private void selectLanguage(int select) {

        LocalManageUtil.saveSelectLanguage(context, select);


        //保存选择语言吧到本地
        String language = LocalManageUtil.getSelectLanguage(context);
        if (language.equals("ENGLISH")) {//英语  @"en"
            CacheUtils.getInstance().put(CacheConstants.Lang, "en");
        } else if (language.equals("简体中文")) {
            CacheUtils.getInstance().put(CacheConstants.Lang, "zh-Hans");
        } else if (language.equals("繁體中文")) {
            CacheUtils.getInstance().put(CacheConstants.Lang, "zh-Hant");
        }
        LogUtils.i("===========语言==" + language);
        MainActivity.reStart(this);

    }


}
