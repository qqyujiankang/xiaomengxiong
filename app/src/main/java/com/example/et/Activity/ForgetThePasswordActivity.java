package com.example.et.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Verification;
import com.example.et.util.lifeful.Lifeful;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码
 */
public class ForgetThePasswordActivity extends BaseActivity {

    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_cot)
    TextView tvGetCot;
    @BindView(R.id.et_get_code)
    EditText etGetCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.public_button)
    Button publicButton;
    private Context context;
    private Lifeful lifeful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifeful=this;
        context=this;
        setContentView(R.layout.activity_forget_the_password);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_get_cot, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_cot:
                new Verification(context,lifeful, Constant.registermessage,tvGetCot,2);
                break;
            case R.id.public_button:
                break;
        }
    }
}
