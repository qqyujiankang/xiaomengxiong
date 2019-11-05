package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * 忘记密码
 */
public class ForgetThePasswordActivity extends BaseActivity {


    @BindView(R.id.et_phone)
    EditText etPhone;
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

    private String phone, Password, ConfirmPassword, GetCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifeful = this;
        context = this;
        setContentView(R.layout.activity_forget_the_password);
        ButterKnife.bind(this);
        initView();

    }

    @Override
    public void initView() {
        super.initView();
        publicButton.setText(getString(R.string.confirm));
    }

    @OnClick({R.id.tv_get_cot, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_cot:
                if (!StringUtils.isEmpty(etPhone.getText().toString().trim())) {

                    new Verification(context, lifeful, Constant.registermessage, tvGetCot, 2,etPhone.getText().toString().trim());
                } else {
                    ToastUtils.showShort(R.string.phoenumber_or_email_account);
                }
                break;
            case R.id.public_button:
                if (examine()) {
                    redata();
                }
                break;
            default:
        }
    }

    /**
     * 检查信息是否全
     *
     * @return
     */
    private boolean examine() {
        phone = etPhone.getText().toString().trim();
        Password = etPassword.getText().toString();
        ConfirmPassword = etConfirmPassword.getText().toString();
        GetCode = etGetCode.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            ToastUtils.showShort(R.string.phoenumber_or_email_account);
            return false;
        }
        if (StringUtils.isEmpty(Password)) {
            ToastUtils.showShort(R.string.enter_your_PIN);
            return false;
        }
        if (StringUtils.isEmpty(ConfirmPassword)) {
            ToastUtils.showShort(R.string.Please_enter_your_new_password);
            return false;
        }
        if (StringUtils.isEmpty(GetCode)) {
            ToastUtils.showShort(R.string.Please_enter_verification_code);
            return false;
        }
        if (!Password.equals(ConfirmPassword)) {
            ToastUtils.showShort(R.string.The_two_password_do_not_match);
            return false;
        }

        return true;
    }

    private void redata() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", phone);
            jsonObject.put("phonecode", GetCode);
            jsonObject.put("types", "game");
            jsonObject.put("pass", Password);
            jsonObject.put("passtwo", ConfirmPassword);


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
