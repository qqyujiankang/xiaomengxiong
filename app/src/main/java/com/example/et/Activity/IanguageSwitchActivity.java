package com.example.et.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.Ustlis.LocalManageUtil;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ianguage_switch);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.language_switch));
    }

    @OnClick({R.id.public_back, R.id.Rl_, R.id.Rl_1, R.id.Rl_2})
    public void onViewClicked(View view) {
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
        LocalManageUtil.saveSelectLanguage(this, select);
        MainActivity.reStart(this);
    }


}
