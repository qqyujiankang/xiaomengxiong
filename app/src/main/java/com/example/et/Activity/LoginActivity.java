package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.Lingondadaper;
import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.DeviceUtils;
import com.example.et.Ustlis.JsonUtils;
import com.example.et.Ustlis.SPUtil;
import com.example.et.Ustlis.SpUtils;
import com.example.et.Ustlis.StringUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.Verification;
import com.example.et.View.LanguageSettingDialog;
import com.example.et.View.LoginPopuWindow;
import com.example.et.entnty.UserData;
import com.example.et.entnty.UserModel;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.SPUtils;
import com.example.et.util.SharedPreferencesHelper;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.constant.KeyValueConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.tv_get_cot)
    TextView tvGetCot;
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.tv_forget_the_password)
    TextView tvForgetThePassword;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.ll_verification_code)
    LinearLayout llVerificationCode;
    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.tva)
    TextView tva;
    @BindView(R.id.Rl)
    RelativeLayout Rl;
    @BindView(R.id.remember)
    CheckBox remember;
    private Context context;
    private Lifeful lifeful;
    private HashMap<String, String> map;
    public static LoginPopuWindow loginPopuWindow;
    LanguageSettingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = LoginActivity.this;
        lifeful = LoginActivity.this;
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        //初始化view
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        publicButton.setText(getString(R.string.login));
        remember.setChecked(true);


        initData();  //读取已经记住的用户名与密码
    }

    private void initData() {

        userModel = JsonUtils.StrToJson(SpUtils.getString(this, SpUtils.login, ""), UserModel.class);

        if (userModel != null && !userModel.equals("")) {

            userBeanList = userModel.getData_list();
            Log.i("exp_", "成功0" + JsonUtils.StrToJson(SpUtils.getString(this, SpUtils.login, "")));


        }

    }


    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_get_cot, R.id.public_button, R.id.tv_forget_the_password, R.id.tv_register, R.id.tv_language, R.id.tva})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tva:
                if (userModel != null) {
                    loginPopuWindow = new LoginPopuWindow(context, userModel, onItemClickListener);
                    loginPopuWindow.setWidth(etPhone.getWidth());
                    loginPopuWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                    loginPopuWindow.showAsDropDown(Rl, 0, 0);
                }


                break;
            case R.id.tv_language:
                if (dialog == null) {
                    dialog = new LanguageSettingDialog(LoginActivity.this, LoginActivity.this);
                }
                dialog.show();
                break;
            case R.id.tv_get_cot:

                if (CheckTheValidation(1)) {
                    new Verification(context, lifeful, Constant.registermessage, tvGetCot, 5, phone);
                }
                break;
            case R.id.public_button:


                if (CheckTheValidation(1)) {

                    requestDatas(2);
                }
//
                break;
            case R.id.tv_forget_the_password:
                intent.setClass(LoginActivity.this, ForgetThePasswordActivity.class);
                ActivityUtils.startActivity(intent);
                break;
            case R.id.tv_register:
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("id", 1);
                ActivityUtils.startActivity(intent);
                break;

            default:
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void requestDatas(int i) {
        String url = "";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", phone);
            jsonObject.put("phonecode", code);
            jsonObject.put("pass", Password);
            jsonObject.put("equipment", DeviceUtils.getAndroidID());


            url = Constant.login;


            TaskPresenterUntils.lifeful(url, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("exp========", success + "==============" + i);
                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, true).getStringMap();

                    if (resultMap.get(KeyValueConstants.CODE).equals("200")) {
                        CacheUtils.getInstance().put(CacheConstants.TOKEN, (CacheUtils.getInstance().getString("token")));
                        CacheUtils.getInstance().put(CacheConstants.PHONE, phone);
                        if (remember.isChecked()) {
                            login1();
                        }

                        ActivityUtils.startActivity(MainActivity.class);
                        finish();
                    } else if (resultMap.get(KeyValueConstants.CODE).equals("402")) {
                        llVerificationCode.setVisibility(View.VISIBLE);
                    }


                    ToastUtils.showShort(resultMap.get(KeyValueConstants.MSG).toString());

                }

            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private UserModel userModel;

    private List<UserModel.UserBean> userBeanList;

    private void login1() {
        //登录成功后报存新数据;
        boolean new_user = true;
        Log.i("exp_", "成功1");
        if (userBeanList == null) {
            userBeanList = new ArrayList<>();
        }

        for (int a = 0; a < userBeanList.size(); a++) {

            if (userBeanList.get(a).getPhoneOremail().equals(phone)) {
                Log.i("exp_", "成功2");
                new_user = false;
                break;
            }

        }
        if (new_user) {

            UserModel.UserBean user3 = new UserModel.UserBean();
            user3.setPhoneOremail(phone);
            user3.setPassWord(Password);
            userBeanList.add(user3);

            if (userModel == null) {
                userModel = new UserModel();
            }

            userModel.setData_list(userBeanList);

            String s = JsonUtils.StrToJson(userModel);

            Log.i("exp_", "成功2" + s);
            if (s != null) {

                SpUtils.putString(this, SpUtils.login, s);

            }
        }


    }

    /**
     * 检查信息
     */
    //手机号
    private String phone;
    //验证码
    private String code;
    private String Password;

    private boolean CheckTheValidation(int i) {
        code = etCode.getText().toString().trim();
        phone = etPhone.getText().toString().trim();
        Password = etPassword.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            ToastUtils.showShort(R.string.Please_enter_your_cell_phone_number);
            return false;
        }
        if (i == 2) {
            if (StringUtils.isEmpty(code)) {
                ToastUtils.showShort(R.string.Please_enter_verification_code);
                return false;
            }
            if (StringUtils.isEmpty(Password)) {
                ToastUtils.showShort(R.string.enter_your_PIN);
                return false;
            }
        }

        return true;
    }

    @Override
    public void requestDatas() {
        super.requestDatas();

    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
            ManagerAdapter managerAdapter = null;
            if (parent.getAdapter() instanceof HeaderViewListAdapter) {

                HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
                managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
            } else if (parent.getAdapter() instanceof ManagerAdapter) {
                managerAdapter = (ManagerAdapter) parent.getAdapter();
            }
            UserModel.UserBean userData = (UserModel.UserBean) managerAdapter.getItem(position);
            String username = userData.getPhoneOremail();
            String pwd = userData.getPassWord();

            etPhone.setText(username);
            etPassword.setText(pwd);


            // 选择后，popupwindow自动消失
            loginPopuWindow.dismiss();
        }
    };

}

