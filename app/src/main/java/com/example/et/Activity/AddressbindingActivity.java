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
 * USDT地址绑定
 */
public class AddressbindingActivity extends BaseActivity {


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
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_cot)
    TextView tvGetCot;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.ll_binding_usDt)
    LinearLayout llBindingUsDt;
    private Context context;
    private Lifeful lifeful;
    private int id;
    private String addressbinding, tepy = "", stringname, xiugan;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        lifeful = this;
        setContentView(R.layout.activity_addressbinding);
        ButterKnife.bind(this);
        getIntentDatas();
        initView();

        LogUtils.i("============" + "0xqwertyuiolkjhgfdsazxcvbnm147258369qwertQ".length());

    }

    @Override
    public void getIntentDatas() {
        super.getIntentDatas();
        id = getIntent().getIntExtra("id", 0);
        addressbinding = getIntent().getStringExtra("name");
        tepy = getIntent().getStringExtra("tepy");
        stringname = getIntent().getStringExtra("stringname");
        xiugan = getIntent().getStringExtra("xiugan");
    }

    @Override
    public void initView() {
        super.initView();
        if (xiugan != null) {
            publicTitleTv.setText(stringname + getString(R.string.revision_Binding_address));
            tv.setText(stringname + getString(R.string.site));
            //  etPassword.setHint(getString(R.string.please_enter) + stringname + getString(R.string.site));
            etPassword.setText(addressbinding);
        } else if (tepy != null) {
            tv.setText(addressbinding + getString(R.string.site));
            etPassword.setHint(getString(R.string.please_enter) + addressbinding + getString(R.string.site));

            llBindingUsDt.setVisibility(View.VISIBLE);
            publicTitleTv.setText(addressbinding + getString(R.string.address_binding));
        } else {
            publicTitleTv.setText(getString(R.string.USDT_address_binding));
        }
        publicButton.setText(getString(R.string.confirm));
    }

    @OnClick({R.id.public_back, R.id.tv_get_cot, R.id.public_button, R.id.public_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;

            case R.id.tv_get_cot:

                new Verification(context, lifeful, Constant.registermessage, tvGetCot, 4, null);
//


                break;
            case R.id.public_button:
                if (ischeis()) {
                    datat();
                }


                break;
            case R.id.public_other:
                llBindingUsDt.setVisibility(View.VISIBLE);

                publicOther.setVisibility(View.GONE);

                break;
            default:
        }
    }

    private boolean ischeis() {
        if (StringUtils.isEmpty(etPassword.getText().toString().trim())) {
            if (addressbinding != null) {
                ToastUtils.showShort(getString(R.string.please_enter) + addressbinding + getString(R.string.site));
                return false;
            } else {
                ToastUtils.showShort(R.string.Please_enter_the_USDT_address);
                return false;
            }

        }
        if (StringUtils.isEmpty(etCode.getText().toString().trim())) {
            ToastUtils.showShort(R.string.Please_enter_verification_code);
            return false;
        }
        return true;
    }

    String url;

    private void datat() {
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            if (id == 0) {
                jsonObject.put("type", "usdt");
            } else {
                jsonObject.put("type", id);
            }
            if (xiugan != null) {
                if (id == 8) {
                    //jsonObject.put("yaddress", addressbinding);
                    jsonObject.put("type", "usdt");
                    url = Constant.upgoldaddress;
                } else if (id == 7) {
                    jsonObject.put("yaddress", addressbinding);
                    url = Constant.upgoldaddress1;
                }
            } else {
                url = Constant.upgoldaddress;
            }
            jsonObject.put("address", etPassword.getText().toString().trim());
            jsonObject.put("code", etCode.getText().toString());

            LogUtils.i("x===========" + url + "====================" + jsonObject);
            TaskPresenterUntils.lifeful(url, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, true).getStringMap();
                    if (resultMap.get(KeyValueConstants.CODE).equals("200")) {
                        CacheUtils.getInstance().put(CacheConstants.usdtaddress, etPassword.getText().toString().trim());
                        HopePropertyActivityActivity.aBoolean = true;
                        finish();
                    }
                    ToastUtils.showShort(resultMap.get(KeyValueConstants.MSG).toString());
                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
