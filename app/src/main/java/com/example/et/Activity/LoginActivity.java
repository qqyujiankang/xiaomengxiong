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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.DeviceUtils;
import com.example.et.Ustlis.StringUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.Verification;
import com.example.et.View.LanguageSettingDialog;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
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
import java.util.List;
import java.util.Map;

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
    private SharedPreferences sPreferences;
    private Map<String, String> map;
    private ListView listView;
    private PopupWindow pw;
    private int width, i;
    private LinearLayout  option;

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


        //读取已经记住的用户名与密码
        sPreferences = getSharedPreferences("session", MODE_PRIVATE);
        map = (Map<String, String>) sPreferences.getAll();
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < (map.size() / 2); i++) {
            String name = sPreferences.getString("name" + i, "");
            list.add(name);
        }


        // 用4个参数的指定，哪个listview中的textview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.items, R.id.item, list);
        option = (LinearLayout) getLayoutInflater().inflate(R.layout.simple_item,
                null);
        // 要在这个linearLayout里面找listView......
        listView = (ListView) option.findViewById(R.id.lv);
        listView.setAdapter(adapter);


        //获取屏幕的宽度并设置popupwindow的宽度为width,我这里是根据布局控件所占的权重
        WindowManager wManager = (WindowManager) getSystemService(this.WINDOW_SERVICE);
        width = wManager.getDefaultDisplay().getWidth() * 4 / 5;


        //实例化一个popupwindow对象
        pw = new PopupWindow(option, width, WindowManager.LayoutParams.WRAP_CONTENT, true);
        ColorDrawable dw = new ColorDrawable(00000);
        pw.setBackgroundDrawable(dw);
        pw.setOutsideTouchable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {

                // 获取选中项内容及从sharePreferences中获取对应的密码
                String username = adapterView.getItemAtPosition(position)
                        .toString();
                String pwd = sPreferences.getString("pwd" + position, "");

                etPhone.setText(username);
                etPassword.setText(pwd);

                // 选择后，popupwindow自动消失
                pw.dismiss();
            }

        });
        tva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pw.showAsDropDown(Rl, 15, -4);
            }
        });
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_get_cot, R.id.public_button, R.id.tv_forget_the_password, R.id.tv_register, R.id.tv_language})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
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
                        login();
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

        // 若是第二次打开软件时，将map在size赋给i,若是不是,则i只要实现i++
        if (i == 0) {
            i = map.size()/2;
        }

        // 若有勾选记住，则保存
        if (remember.isChecked()) {
            String name = etPhone.getText().toString().trim();
            String pwd = etPassword.getText().toString().trim();
            if (!"".equals(name) && !"".equals(pwd)) {

                sPreferences.edit().putString("name" + i, name)
                        .putString("pwd" + i, pwd).commit();
                i++;



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
}
