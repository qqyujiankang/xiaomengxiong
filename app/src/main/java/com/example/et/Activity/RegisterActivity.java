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
import com.example.et.Ustlis.CountDownTimerUtils;
import com.example.et.Ustlis.StringUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.entnty.Areacode;
import com.example.et.entnty.Pagebean;
import com.example.et.util.LogUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.KeyValueConstants;
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
 * 注册
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.spinner)
    TextView spinner;
    @BindView(R.id.tv_get_cot)
    TextView tvGetCot;
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_ok)
    EditText etPasswordOk;
    @BindView(R.id.et_payment_code)
    EditText etPaymentCode;
    @BindView(R.id.et_payment_code_ok)
    EditText etPaymentCodeOk;
    @BindView(R.id.et_invitation_code)
    EditText etInvitationCode;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = RegisterActivity.this;
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        requestDatas();
    }

    @Override
    public void initView() {
        super.initView();
        publicButton.setText("注册");
    }


    @Override
    public void requestDatas() {
        super.requestDatas();
        requestDatas(0);

    }

    @OnClick({R.id.tv_get_cot, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_cot:
                if (CheckTheValidation(1)) {
                    requestDatas(1);
                }

                break;
            case R.id.public_button:
                if (CheckTheValidation(2)) {
                    requestDatas(2);
                }

                break;
            default:
        }
    }

    private void requestDatas(int i) {
        String url = "";

        try {
            JSONObject jsonObject = new JSONObject();
            if (i == 1 || i == 2) {
                jsonObject.put("phone", phone);
                jsonObject.put("quhao", "86");
                if (i == 1) {
                    jsonObject.put("types", "1");
                    url = Constant.registermessage;
                } else if (i == 2) {
                    url = Constant.register;
                    jsonObject.put("pass", Password);
                    jsonObject.put("passtwo", PasswordOK);
                    jsonObject.put("pay_pass", paymentcode);
                    jsonObject.put("pay_passtwo", paymentcodeok);
                    jsonObject.put("name", nickname);
                    jsonObject.put("f_code", invitationcode);
                    jsonObject.put("phonecode", code);
                    jsonObject.put("photo", "");
                }


            } else {
                jsonObject.put("", "4");
                url = Constant.quhao;
            }
            LogUtils.i("exp======zhuce===" + jsonObject + "==========" + url);

            //"phone="+phone+"&quhao="+86+"&types="+1
            TaskPresenterUntils.lifeful(url, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("exp========", success);

                    if (i == 1) {
                        Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();

                        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(tvGetCot, 60, 1000);
                        countDownTimerUtils.start();

                        ToastUtils.showShort(resultMap.get(KeyValueConstants.MSG).toString());
                        finish();

                    } else if (i == 0) {
                        Pagebean<Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, Areacode[].class, false);
                        LogUtils.i("exp==========quanhao" + objectPagebean.getList());

                    }


                }

            }, this));
        } catch (
                JSONException e) {
            e.printStackTrace();
        }

    }

    //手机号
    private String phone;
    //验证码
    private String code;
    //昵称
    private String nickname;
    //密码
    private String Password;
    //再次输入密码
    private String PasswordOK;
    //支付密码
    private String paymentcode;
    //再次输入支付密码
    private String paymentcodeok;
    //邀请码；
    private String invitationcode;

    /***
     * 检查数据
     * @return
     */
    private boolean CheckTheValidation(int i) {
        code = etCode.getText().toString().trim();
        phone = etPhone.getText().toString().trim();
        nickname = etNickname.getText().toString();
        Password = etPassword.getText().toString();
        PasswordOK = etPasswordOk.getText().toString();
        paymentcode = etPaymentCode.getText().toString();
        paymentcodeok = etPaymentCodeOk.getText().toString();
        invitationcode = etInvitationCode.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            ToastUtils.showShort(R.string.Please_enter_your_cell_phone_number);
            return false;
        }
        if (i == 2) {
            if (StringUtils.isEmpty(nickname)) {
                ToastUtils.showShort(R.string.Please_enter_nickname);
                return false;
            }
            if (StringUtils.isEmpty(Password)) {
                ToastUtils.showShort(R.string.enter_your_PIN);
                return false;
            }
            if (!PasswordOK.equals(Password)) {
                ToastUtils.showShort("两次密码不一致");
                return false;

            }
            if (Password.length() <= 6 && PasswordOK.length() <= 6) {
                ToastUtils.showShort("密码长度最低6位数");
                return false;
            }
            if (!etPaymentCodeOk.equals(paymentcode)) {
                ToastUtils.showShort("两次支付密码不一致");
                return false;
            }
            if (paymentcode.length() != 6 && etPaymentCodeOk.length() != 6) {
                ToastUtils.showShort("密码长度最低6位数");
                return false;
            }
            if (StringUtils.isEmpty(Password)) {
                ToastUtils.showShort("密码长度最低6位数");
                return false;
            }

        }

        return true;
    }
}
