package com.example.et.Activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ImageLoaderUtil;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
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
 * 充值
 */
public class TopupActivity extends BaseActivity {

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
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.iv_address_img)
    ImageView ivAddressImg;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
        ButterKnife.bind(this);
        getIntentDatas();
        initView();
    }

    private int id;

    @Override
    public void getIntentDatas() {
        super.getIntentDatas();
        id = getIntent().getIntExtra("id", 0);
        requestDatas();
    }


    @Override
    public void initView() {
        super.initView();
        publicButton.setText(getString(R.string.Copy_the_address));
        publicTitleTv.setText(getString(R.string.top_up));
    }

    @OnClick({R.id.public_back, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.public_button:
                copy(tvAddress);
                break;
        }
    }

    /**
     * 复制
     */
    private void copy(TextView textView) {
        String copyData = textView.getText().toString();
        ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(copyData);
        ToastUtils.showShort(R.string.text69);
    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("type", id);


            TaskPresenterUntils.lifeful(Constant.recharge, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======钱包======" + success);

                    Map<String, Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount(TopupActivity.this, success, null, false).getStringMap();
                    if (objectPagebean.get(KeyValueConstants.CODE).equals("200")) {
                        tvAddress.setText(objectPagebean.get("address").toString());
                        ImageLoaderUtil.loadImage(TopupActivity.this, objectPagebean.get("address_img").toString(), ivAddressImg);

                    }



                }


            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
