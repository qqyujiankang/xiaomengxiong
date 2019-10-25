package com.example.et.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 安全中心
 */
public class SecurityCenterconActivity extends BaseActivity {

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
    @BindView(R.id.Rl_Change_login_password)
    RelativeLayout RlChangeLoginPassword;
    @BindView(R.id.Rl_Set_security_password)
    RelativeLayout RlSetSecurityPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_centercon);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.Security_Centercon));
    }

    @OnClick({R.id.public_back, R.id.Rl_Change_login_password, R.id.Rl_Set_security_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.Rl_Change_login_password:
                ActivityUtils.startActivity(LogiPasswordActivity.class);

                break;
            case R.id.Rl_Set_security_password:
                  ActivityUtils.startActivity(SafePasswordActivity.class);


                break;
        }
    }
}
