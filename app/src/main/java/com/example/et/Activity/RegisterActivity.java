package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.TextView;

import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.CountDownTimerUtils;
import com.example.et.Ustlis.StringUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.Verification;
import com.example.et.View.AreaDiog;
import com.example.et.entnty.Areacode;
import com.example.et.entnty.Pagebean;
import com.example.et.util.LogUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.KeyValueConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
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
    @BindView(R.id.cb_phone)
    CheckBox cbPhone;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.spinner1)
    TextView spinner1;

    private Context context;
    private Lifeful lifeful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = RegisterActivity.this;
        lifeful = RegisterActivity.this;
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        requestDatas();
    }

    @Override
    public void initView() {
        super.initView();
        publicButton.setText(R.string.tet1);
        cbPhone.setChecked(true);
    }

    Pagebean<Object> objectPagebean;

    @Override
    public void requestDatas() {
        super.requestDatas();
        requestDatas(0);
    }

    @OnClick({R.id.tv_get_cot, R.id.public_button, R.id.cb_phone, R.id.cb_check, R.id.spinner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.spinner:
                areacodes = ((ArrayList<Areacode>) (ArrayList) objectPagebean.getList());
                areaDiog = new AreaDiog(context, onItemClickListener, areacodes);
                areaDiog.show();
                break;
            case R.id.tv_get_cot:
                if (CheckTheValidation(1)) {
                    new Verification(context, lifeful, Constant.registermessage, tvGetCot, 1, phone);

                }

                break;

            case R.id.public_button:
                if (CheckTheValidation(2)) {
                    requestDatas(2);
                }

                break;
            case R.id.cb_phone:
                cbPhone.setChecked(true);
                cbCheck.setChecked(false);
                tvPhone.setText(R.string.phone);
                spinner.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.VISIBLE);

                etPhone.setHint(R.string.Please_enter_your_cell_phone_number);

                break;
            case R.id.cb_check:
                cbCheck.setChecked(true);
                cbPhone.setChecked(false);
                tvPhone.setText(R.string.mailbox);
                spinner.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);
                etPhone.setHint(R.string.Please_enter_email);
                break;
            default:
        }
    }

    private List<Areacode> areacodes;
    private String quhao = "86";
    AreaDiog areaDiog;

    private void requestDatas(int i) {
        String url = "";

        try {
            JSONObject jsonObject = new JSONObject();

            if (i == 0) {
                url = Constant.quhao;
                jsonObject.put("", "4");
            } else if (i == 2) {
                jsonObject.put("phone", phone);
                jsonObject.put("quhao", quhao);
                jsonObject.put("pass", Password);
                jsonObject.put("passtwo", PasswordOK);
                jsonObject.put("pay_pass", paymentcode);
                jsonObject.put("pay_passtwo", paymentcodeok);
                jsonObject.put("name", nickname);
                jsonObject.put("f_code", invitationcode);
                jsonObject.put("phonecode", code);
                jsonObject.put("photo", "");

                jsonObject.put("", "4");
                url = Constant.register;
            }


            TaskPresenterUntils.lifeful(url, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("exp========", success);

                    if (i == 2) {
                        Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();

                        ToastUtils.showShort(resultMap.get(KeyValueConstants.MSG).toString());
                        finish();

                    } else if (i == 0) {
                        objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, Areacode[].class, false);

                    }


                }

            }, this));
        } catch (
                JSONException e) {
            e.printStackTrace();
        }

    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
            ManagerAdapter managerAdapter = null;
            if (parent.getAdapter() instanceof HeaderViewListAdapter) {
                HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
                managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
            } else if (parent.getAdapter() instanceof ManagerAdapter) {
                managerAdapter = (ManagerAdapter) parent.getAdapter();
            }
            Areacode areacode = (Areacode) managerAdapter.getItem(i);
            quhao = areacode.getMobile_prefix();
            spinner.setText("+" + quhao);
            areaDiog.dismiss();


        }
    };
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
                ToastUtils.showShort(R.string.The_two_password_do_not_match);
                return false;

            }
            if (Password.length() <= 6 && PasswordOK.length() <= 6) {
                ToastUtils.showShort(getString(R.string.Minimum_password_length));
                return false;
            }
            if (!paymentcodeok.equals(paymentcode)) {
                ToastUtils.showShort(getString(R.string.The_two_payment_passwords_are_inconsistent));
                return false;
            }
            if (paymentcode.length() != 6 && etPaymentCodeOk.length() != 6) {
                ToastUtils.showShort(getString(R.string.Paymentpassword_length_minimum_6_digits));
                return false;
            }
            if (StringUtils.isEmpty(Password)) {
                ToastUtils.showShort(R.string.Minimum_password_length);
                return false;
            }

        }

        return true;
    }


}
