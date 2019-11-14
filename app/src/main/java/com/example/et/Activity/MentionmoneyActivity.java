package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ScreenUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.View.PayDialog;
import com.example.et.util.CacheUtils;
import com.example.et.util.JsonUtil;
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
    TextView etPassword;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_cot)
    TextView tvGetCot;
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.tv_Withdrawal_amount)
    TextView tvWithdrawalAmount;
    @BindView(R.id.tv_data)
    TextView tvData;
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

        requestDatas2();
    }

    @Override
    public void requestDatas2() {
        super.requestDatas2();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("type", id);


            TaskPresenterUntils.lifeful(Constant.getgold_hl, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======getgold_hl========" + success);
                    Map<String, Object> stringObjectMap = JsonUtil.parseJSON(success);
                    tvData.setText(getString(R.string.service_charge) + stringObjectMap.get("data").toString() + getString(R.string.shouxufen));


                }

            }, this));
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
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
        etCode.setHint(getString(R.string.The_balance_can_be_transferred_out) + Number + stringname);
        tvWithdrawalAmount.setText(getString(R.string.Withdrawal_amount) + stringname);
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
                if (!StringUtils.isEmpty(etCode.getText().toString())) {
                    if (Double.parseDouble(etCode.getText().toString()) >= 100) {

                        PayDialog payDialog = new PayDialog(context) {
                            @Override
                            public void clickCallBack(String str) {
                                pay_pass = str;
                                if (!StringUtils.isEmpty(pay_pass)) {
                                    requestDatas();
                                } else {
                                    ToastUtils.showShort(R.string.phoenumber_payment_code_ok);
                                }

                            }

                            @Override
                            public void clickBack() {

                            }
                        };
                        payDialog.getWindow().setLayout(ScreenUtils.getScreenWidth()-200 , LinearLayout.LayoutParams.WRAP_CONTENT);
                        payDialog.setCancelable(false);
                        payDialog.setView(new EditText(context));
                        payDialog.show();

                    } else {
                        ToastUtils.showShort(getString(R.string.tets1));
                    }
                }
                break;
            default:
        }


    }

    private String add;
    private String munn;


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
