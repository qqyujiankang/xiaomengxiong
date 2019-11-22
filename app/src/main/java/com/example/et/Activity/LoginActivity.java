package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.Lingondadaper;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.DeviceUtils;
import com.example.et.Ustlis.SPUtil;
import com.example.et.Ustlis.SpUtils;
import com.example.et.Ustlis.StringUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.Verification;
import com.example.et.View.LanguageSettingDialog;
import com.example.et.entnty.UserData;
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

public class LoginActivity extends BaseActivity implements AdapterView.OnItemClickListener {


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
    private SharedPreferences sPreferences;
    private Map<String, String> map;
    private ListView listView;
    public static PopupWindow pw;
    private int v;
    private View option;
    SharedPreferencesHelper helper;
    private List<UserData> list = new ArrayList<>();

    private static boolean aBoolean;
    Lingondadaper adapter;

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
        if (aBoolean == true) {
            remember.setChecked(true);
        }
        lodg();
        //读取已经记住的用户名与密码

    }

    public void lodg() {

        map = (Map<String, String>) SpUtils.getAll(context);
        LogUtils.i("map=======1====" + map.size());
        for (int i = 0; i < map.size(); i++) {

            String name = SpUtils.getString(context, "name" + i, "");
            String pwd = SpUtils.getString(context, "pwd" + i, "");
            String pwd1 = SpUtils.getString(context, "tepy" + i, "");
            LogUtils.i("exp=============" + name + "=============" + pwd + "===========" + pwd1);
            if (!name.equals("") && !pwd.equals("")) {

                list.add(new UserData(name, pwd, Integer.parseInt(pwd1)));
            }


        }
//        Set set = new LinkedHashSet<UserData>();
//        set.addAll(list);
//        list.clear();
//        list.addAll(set);


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
                adapter = new Lingondadaper(context, list, null);
                option = getLayoutInflater().inflate(R.layout.simple_item, null);
                // 要在这个linearLayout里面找listView......
                listView = (ListView) option.findViewById(R.id.lv);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(this);

                ColorDrawable dw = new ColorDrawable(0000000000);
                // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作


                //实例化一个popupwindow对象
                pw = new PopupWindow(option, etPhone.getWidth(), WindowManager.LayoutParams.WRAP_CONTENT, true);
                pw.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_radius15_gray));
                pw.setOutsideTouchable(true);
                pw.setBackgroundDrawable(dw);
                pw.showAsDropDown(Rl, 0, 0);
                break;
            case R.id.tv_language:
                LanguageSettingDialog dialog = new LanguageSettingDialog(LoginActivity.this, LoginActivity.this);
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

                            login();

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

    private void login() {
        LogUtils.i("map===========" + map.size());


        if (map.size() != 0) {
            for (int i = 0; i < map.size(); i++) {

                String name = SpUtils.getString(context, "name" + i, "");
                String pwd = SpUtils.getString(context, "pwd" + i, "");
                String pwd1 = SpUtils.getString(context, "tepy" + i, "");
                LogUtils.i("exp=============" + name + "=============" + pwd + "===========" + pwd1);
                if (!name.equals("") && !pwd.equals("") && !name.equals(phone)) {
                    int k = i + 1;
                    SpUtils.putString(context, "name" + k, phone);
                    SpUtils.putString(context, "pwd" + k, Password);
                    String s = String.valueOf(i);
                    SpUtils.putString(context, "tepy" + k, s);

                    aBoolean = true;
                    // list.add(new UserData(name, pwd, Integer.parseInt(pwd1)));
                }


            }
        } else {
            SpUtils.putString(context, "name" + 0, phone);
            SpUtils.putString(context, "pwd" + 0, Password);
            SpUtils.putString(context, "tepy" + 0, v + "");

            aBoolean = true;
        }
//        if (list.size() > 0) {
//            for (int key = 0; key < list.size(); key++) {
//                LogUtils.i("exp=======" + "===" + key + "=======" + list.get(key).getAcount());
//                if (!list.get(key).getAcount().equals(phone)) {
//
//                    LogUtils.i("exp=======" + "===" + key + "=======" + list.get(key).getAcount());
//
//                    LogUtils.i("exp=====2======" + v, phone, "==========" + "=====Password==="
//                            + Password + "========" + SpUtils.getString(context, "tepy" + v, ""));
//                }
//
//
//            }
//
//
//        } else {
//
//            LogUtils.i("exp=====ba======" + v, phone, "==========" + "=====Password===" + Password);
//        }


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


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        // 获取选中项内容及从sharePreferences中获取对应的密码
        UserData userData = (UserData) adapter.getItem(position);
        String username = userData.getAcount();
        String pwd = userData.getPasswd();

        etPhone.setText(username);
        etPassword.setText(pwd);


        // 选择后，popupwindow自动消失
        pw.dismiss();
    }
}

