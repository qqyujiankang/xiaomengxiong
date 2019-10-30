package com.example.et.Activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.Contractrecordadapetr;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.entnty.Contract;
import com.example.et.entnty.Contractrecord;
import com.example.et.entnty.Pagebean;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.AdapterRealize;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 合约记录
 */
public class ContractrecordActivity extends BaseActivity {

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
    @BindView(R.id.lv_contern)
    ListView lvContern;
    Context context;
    private AdapterRealize adapterRealize;
    private Lifeful lifeful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        lifeful = ContractrecordActivity.this;
        setContentView(R.layout.activity_contractrecord);
        ButterKnife.bind(this);
        initView();
        requestDatas();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.Contract_record));
    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            TaskPresenterUntils.lifeful(Constant.mycontract, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("=======合约记录====" + success);
                    if (adapterRealize == null) {
                        adapterRealize = new AdapterRealize();
                    }

                    Pagebean<Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount(ContractrecordActivity.this, success, Contractrecord[].class, false);

                    adapterRealize.AdapterSetListDatas(objectPagebean, 0, lvContern, context, Contractrecordadapetr.class, lifeful);//返回第几页


                }

            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.public_back)
    public void onViewClicked() {
        finish();
    }
}
