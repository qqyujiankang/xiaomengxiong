package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.Verification;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.StringUtils;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 安全密码
 */
public class SafePasswordActivity extends BaseActivity {

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
    @BindView(R.id.tv_get_cot)
    TextView tvGetCot;
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_original_password)
    EditText etoriginalPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private Context context;
    private Lifeful lifeful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        lifeful = this;
        setContentView(R.layout.activity_safe_password);
        ButterKnife.bind(this);
        initView();

    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.security_code));
        publicButton.setText(getString(R.string.confirm));

    }

    @OnClick({R.id.public_back, R.id.tv_get_cot, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();

                break;
            case R.id.tv_get_cot:
                new Verification(context, lifeful, Constant.registermessage, tvGetCot, 3);
                break;
            case R.id.public_button:
                if (examine()) {
                    redata();
                }
                break;
        }
    }

    /**
     * 检查信息是否全
     *
     * @return
     */
    String code, OriginalPassword, NewPassword;

    private boolean examine() {
        OriginalPassword = etoriginalPassword.getText().toString();
        NewPassword = etNewPassword.getText().toString();
        code = etCode.getText().toString();

        if (StringUtils.isEmpty(OriginalPassword)) {
            ToastUtils.showShort(R.string.Please_enter_the_original_password);
            return false;
        }
        if (StringUtils.isEmpty(NewPassword)) {
            ToastUtils.showShort(R.string.Please_enter_your_new_password);
            return false;
        }
        if (StringUtils.isEmpty(code)) {
            ToastUtils.showShort(R.string.Please_enter_verification_code);
            return false;
        }
        if (!NewPassword.equals(OriginalPassword)) {
            ToastUtils.showShort(R.string.The_two_password_do_not_match);
            return false;
        }

        return true;
    }

    private void redata() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("phonecode", code);
            jsonObject.put("types", "pay");
            jsonObject.put("pass", OriginalPassword);
            jsonObject.put("passtwo", NewPassword);


            TaskPresenterUntils.lifeful(Constant.getpass, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======钱包======" + success);


                    Map<String, Object> stringMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();
                    if (stringMap.get(KeyValueConstants.CODE).equals("200")) {
                        finish();
                    }
                    ToastUtils.showShort(stringMap.get(KeyValueConstants.MSG).toString());

                }


            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
