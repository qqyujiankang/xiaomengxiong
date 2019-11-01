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
import com.example.et.View.PayDialog;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.StringUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
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
 * 提币
 */
public class MentionmoneyActivity extends BaseActivity {

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
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_cot)
    TextView tvGetCot;
    @BindView(R.id.public_button)
    Button publicButton;
    private String name, Number, stringname, pay_pass;

    private int id;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MentionmoneyActivity.this;
        setContentView(R.layout.activity_mentionmoney);
        ButterKnife.bind(this);
        getIntentDatas();

        initView();
    }

    @Override
    public void getIntentDatas() {
        super.getIntentDatas();
        name = getIntent().getStringExtra("name");
        id = getIntent().getIntExtra("id", 0);
        Number = getIntent().getStringExtra("Number");
        stringname = getIntent().getStringExtra("stringname");

    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.Mention_money));
        publicButton.setText(getString(R.string.Mention_money));
        etPassword.setText(name);
        etCode.setHint("可转出余额" + Number + stringname);
    }

    @OnClick({R.id.public_back, R.id.tv_get_cot, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.tv_get_cot:
                etCode.setText(Number);
                break;
            case R.id.public_button:
                if (!StringUtils.isEmpty(etCode.getText().toString().trim())) {

                    PayDialog payDialog = new PayDialog(context) {
                        @Override
                        public void clickCallBack(String str) {
                            pay_pass = str;
                            requestDatas();
                        }

                        @Override
                        public void clickBack() {

                        }
                    };
                    payDialog.setCancelable(false);
                    payDialog.setView(new EditText(context));
                    payDialog.show();

                } else {
                    ToastUtils.showShort(R.string.Please_enter_amount);
                }
                break;
            default:
        }


    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("addressid", etPassword.getText().toString());
            jsonObject.put("type", id);
            jsonObject.put("number", etCode.getText().toString());
            jsonObject.put("pay_pass", pay_pass);


            TaskPresenterUntils.lifeful(Constant.getgold, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {

                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, true).getStringMap();
                    LogUtils.i("exp========", success + "==============" + resultMap.get(KeyValueConstants.MSG).toString());
                    if (resultMap.get(KeyValueConstants.CODE).equals("200")) {
                        finish();
                    }
                    ToastUtils.showShort(resultMap.get(KeyValueConstants.MSG).toString());

                }

            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
