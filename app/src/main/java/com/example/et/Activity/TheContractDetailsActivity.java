package com.example.et.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.StatusBarUtil;
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

import static com.example.et.Ustlis.StatusBarUtil.setRootViewFitsSystemWindows;

/**
 * 合约详情
 */
public class TheContractDetailsActivity extends BaseActivity {
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
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.Rl_state)
    RelativeLayout RlState;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_prospective)
    TextView tvProspective;
    @BindView(R.id.ll_prospective)
    LinearLayout llProspective;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_currency)
    TextView tvCurrency;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_quantity)
    TextView tvQuantity;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_payment)
    TextView tvPayment;
    @BindView(R.id.Rl_1)
    RelativeLayout Rl1;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.tv_time_1)
    TextView tvTime1;
    @BindView(R.id.Rl_3)
    RelativeLayout Rl3;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.tv_time_0)
    TextView tvTime0;
    @BindView(R.id.Rl_2)
    RelativeLayout Rl2;
    @BindView(R.id.Rl_no)
    LinearLayout RlNo;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.vv_07)
    View vv07;
    private int id;
    private Context context;
    private Lifeful lifeful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        lifeful = this;

        setContentView(R.layout.activity_the_contract_details);
        setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        ButterKnife.bind(this);
        ButterKnife.bind(this);
        getIntentDatas();
        initView();

        requestDatas();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(R.string.menu_find);

    }

    @Override
    public void getIntentDatas() {
        super.getIntentDatas();
        id = getIntent().getIntExtra("id", 0);
    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("hyid", id);

            TaskPresenterUntils.lifeful(Constant.newcontract, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======newcontract======" + success);
                    //adapterRealize = new AdapterRealize();

                    Map<String, Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount(TheContractDetailsActivity.this, success, null, false).getStringMap();
                    LogUtils.i("======newcontract======" + objectPagebean.get(KeyValueConstants.MSG));
                    if (objectPagebean.get(KeyValueConstants.CODE).equals("200")) {

                        LogUtils.i("======newcontract======" + objectPagebean.get("state").toString());
                        if (objectPagebean.get("state").toString().equals("4")) {//预约中
                            tvState.setText(R.string.Contract_completed);
                            tv.setText(R.string.Generated_income);
                            tvProspective.setText(objectPagebean.get("static").toString());
                            tv1.setText(R.string.Open_contract_currency);
                            tvPrice.setText(objectPagebean.get("pre_cur").toString());
                            tv2.setText(R.string.Execute_the_contract_currency);
                            tvCurrency.setText(objectPagebean.get("sup_cur").toString());
                            tv3.setText(R.string.Days_of_actual_earnings);
                            tvQuantity.setText(objectPagebean.get("sjsy_day").toString());
                            tv4.setText(R.string.Appointment_success_time);
                            tvTime.setText(objectPagebean.get("pre_cur_time").toString());
                            tv5.setText(R.string.Matching_success_time);
                            tvPayment.setText(objectPagebean.get("pp_cur_time").toString());
                            tv7.setText(R.string.Execution_success_time);
                            tvTime1.setText(objectPagebean.get("yhzx_cur_time").toString());
                            tv6.setText(R.string.Contract_completion_time);
                            tvTime0.setText(objectPagebean.get("ok_cur_time").toString());
                        } else if (objectPagebean.get("state").toString().equals("3") || objectPagebean.get("state").toString().equals("5")) {
                            tvState.setText(R.string.Contract_executed_successfully);
                            tv.setText(R.string.prospective_earnings);
                            tvProspective.setText(objectPagebean.get("static").toString());
                            tv1.setText(R.string.Make_an_appointment_to_currency);
                            tvPrice.setText(objectPagebean.get("pre_cur").toString());
                            tv2.setText(R.string.To_perform_currency);
                            tvCurrency.setText(objectPagebean.get("sup_cur").toString());
                            tv3.setText(R.string.Days_of_actual_earnings);
                            tvQuantity.setText(objectPagebean.get("sjsy_day").toString());
                            tv4.setText(R.string.Appointment_success_time);
                            tvTime.setText(objectPagebean.get("pre_cur_time").toString());
                            tv5.setText(R.string.Matching_success_time);
                            tvPayment.setText(objectPagebean.get("pp_cur_time").toString());
                            tv7.setText(R.string.Execution_success_time);
                            tvTime1.setText(objectPagebean.get("yhzx_cur_time").toString());
                            Rl2.setVisibility(View.GONE);
                            vv07.setVisibility(View.GONE);
                        }
                        tvMoney.setText(objectPagebean.get("money").toString() + "USDT");
                    }


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
