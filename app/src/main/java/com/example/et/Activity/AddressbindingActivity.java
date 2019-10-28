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
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.ll_binding_usDt)
    LinearLayout llBindingUsDt;
    @BindView(R.id.tv_address_binding)
    TextView tvAddressBinding;
    private Context context;
    private Lifeful lifeful;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        lifeful = this;
        setContentView(R.layout.activity_addressbinding);
        ButterKnife.bind(this);
        initView();

        LogUtils.i("============" + "0xqwertyuiolkjhgfdsazxcvbnm147258369qwertQ".length());

    }



    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.USDT_address_binding));
        publicButton.setText(getString(R.string.confirm));
        if (CacheUtils.getInstance().getString(CacheConstants.usdtaddress) != null) {
            llBindingUsDt.setVisibility(View.GONE);
            tvAddressBinding.setText(CacheUtils.getInstance().getString(CacheConstants.usdtaddress));
            publicOther.setText(getString(R.string.revision_Binding_address));
        }

    }

    @OnClick({R.id.public_back, R.id.tv_get_cot, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;

            case R.id.tv_get_cot:
                new Verification(context, lifeful, Constant.registermessage, tvGetCot, 4);

                break;
            case R.id.public_button:
                datat();
                break;
            default:
        }
    }

    private void datat() {
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("type", "usdt");
            jsonObject.put("address", "0xad023b17ece15a40cf52f91c49ec234b8aca0a42");
            jsonObject.put("code", etCode.getText().toString());

            TaskPresenterUntils.lifeful(Constant.upgoldaddress, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, true).getStringMap();
                    if (resultMap.get(KeyValueConstants.CODE).equals("200")) {
                        CacheUtils.getInstance().put(CacheConstants.PHONE, "0xad023b17ece15a40cf52f91c49ec234b8aca0a42");
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
