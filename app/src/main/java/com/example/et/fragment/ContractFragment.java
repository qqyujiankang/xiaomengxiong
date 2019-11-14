package com.example.et.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.et.Activity.AppointmentcontractActivity;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.ScreenUtils;
import com.example.et.Ustlis.StatusBarUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.View.PayDialog;
import com.example.et.util.BarUtils;
import com.example.et.util.CacheUtils;
import com.example.et.util.CountDown;
import com.example.et.util.LogUtils;
import com.example.et.util.PublicSwipeRefreshLayout.SwipeRefreshLayout;
import com.example.et.util.StringUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.TimeUtils;
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
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 合约
 */

public class ContractFragment extends BaseFragment {


    @BindView(R.id.tv_not_available)
    ImageView tvNotAvailable;
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.Rl_)
    RelativeLayout Rl;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_yy_time)
    TextView tvYyTime;
    @BindView(R.id.Rl_state)
    RelativeLayout RlState;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_currency)
    TextView tvCurrency;
    @BindView(R.id.tv_quantity)
    TextView tvQuantity;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.public_button1)
    Button publicButton1;
    @BindView(R.id.Rl_no)
    LinearLayout RlNo;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_prospective)
    TextView tvProspective;
    @BindView(R.id.ll_prospective)
    LinearLayout llProspective;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.Rl_1)
    RelativeLayout Rl1;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_payment)
    TextView tvPayment;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.tv_time_0)
    TextView tvTime0;
    @BindView(R.id.Rl_2)
    RelativeLayout Rl2;
    @BindView(R.id.Rl_public_button1)
    RelativeLayout RlPublicButton1;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.tv_time_1)
    TextView tvTime1;
    @BindView(R.id.Rl_3)
    RelativeLayout Rl3;
    @BindView(R.id.tv_o)
    TextView tvO;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tvy)
    TextView tvy;
    @BindView(R.id.ll_a)
    LinearLayout llA;
    @BindView(R.id.ll_public_line_01)
    LinearLayout llPublicLine01;
    private Context context;
    Unbinder unbinder;

    private Lifeful lifeful;
    private String oid = "", pay_pass;








    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        lifeful = (Lifeful) getActivity();
        requestDatas();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i("=========123=====wode===");
    }

    CountDown countDown;

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));

            TaskPresenterUntils.lifeful(Constant.newcontract, jsonObject, new OnLoadLifefulListener<String>(swipeRefreshLayout, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======newcontract======" + success);
                    //adapterRealize = new AdapterRealize();

                    Map<String, Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount(getActivity(), success, null, false).getStringMap();
                    LogUtils.i("======newcontract======" + objectPagebean.get(KeyValueConstants.MSG));
                    if (objectPagebean.get(KeyValueConstants.CODE).equals("200")) {
                        Rl.setVisibility(View.GONE);
                        RlNo.setVisibility(View.VISIBLE);
                        LogUtils.i("======newcontract======" + objectPagebean.get("state").toString());
                        if (objectPagebean.get("state").toString().equals("0") || objectPagebean.get("state").toString().equals("1") || objectPagebean.get("state").toString().equals("2")) {


                        }
                        if (objectPagebean.get("state").toString().equals("0")) {//预约中

                            llPublicLine01.setVisibility(View.GONE);
                            oid = objectPagebean.get("id").toString();
                            tvState.setText(getString(R.string.To_make_an_appointment_in));
                            long p = Long.parseLong(objectPagebean.get("yytime_daojishi").toString()) * 1000;
                            countDown = new CountDown(tvYyTime, p - TimeUtils.getNowMills(), 0);
                            countDown.timerStart();

                            tvPrice.setText(StringUtils.calculateProfit(Double.parseDouble(objectPagebean.get("pre_cur_money").toString()), 5));
                            tvCurrency.setText(objectPagebean.get("pre_cur").toString());

                            tvQuantity.setText(StringUtils.calculateProfit(Double.parseDouble(objectPagebean.get("pre_cur_number").toString()), 5));

                            tvTime.setText(objectPagebean.get("yytime").toString());
                        } else if (objectPagebean.get("state").toString().equals("1")) {
                            if (countDown == null) {
                                countDown = new CountDown(tvYyTime, 0 - TimeUtils.getNowMills(), 0);
                            } else {
                                countDown.timerCancel();
                                ;
                            }

                            llPublicLine01.setVisibility(View.VISIBLE);
                            RlPublicButton1.setVisibility(View.GONE);
                            tvState.setText(getString(R.string.To_make_an_appointment_in_1));
                            llProspective.setVisibility(View.VISIBLE);
                            tvProspective.setText(objectPagebean.get("static").toString());
                            tv1.setText(R.string.Open_contract_currency);
                            tvPrice.setText(objectPagebean.get("pre_cur").toString());
                            tv2.setText(R.string.maximum_execution_time);
                            tvCurrency.setText("10天");
                            tv3.setText(R.string.daily_return_rate);
                            tvQuantity.setText(objectPagebean.get("day_shouyilv").toString() + getString(R.string.ts1));
                            tv4.setText(R.string.The_reservation_deposit);
                            tvTime.setText(objectPagebean.get("dingjin").toString() + getString(R.string.USDT));
                            Rl1.setVisibility(View.VISIBLE);
                            tv5.setText(R.string.payment);
                            tvPayment.setText(objectPagebean.get("weikuan").toString() + getString(R.string.USDT));
                            Rl2.setVisibility(View.VISIBLE);
                            tv6.setText(R.string.Appointment_success_time);
                            tvTime0.setText(objectPagebean.get("pre_cur_time").toString());
                            tvDay.setText(R.string.Successful_reservation_days);
                            tvYyTime.setText(objectPagebean.get("okdays").toString() + "天");
                        } else if (objectPagebean.get("state").toString().equals("2")) {
                            llPublicLine01.setVisibility(View.VISIBLE);
                            RlPublicButton1.setVisibility(View.VISIBLE);
                            // oid = objectPagebean.get("id").toString();
                            tvState.setText(R.string.The_match_is_successful);
                            tvDay.setText(R.string.Countdown_to_execution);
                            long p1 = Long.parseLong(objectPagebean.get("hour24").toString()) * 1000;
                            countDown = new CountDown(tvYyTime, p1 - TimeUtils.getNowMills(), 1);
                            countDown.timerStart();
                            //  tvYyTime.setText(objectPagebean.get("okdays").toString());
                            llProspective.setVisibility(View.VISIBLE);
                            tvProspective.setText(objectPagebean.get("static").toString());
                            tv1.setText(R.string.Execute_the_contract_currency);
                            tvPrice.setText(objectPagebean.get("zx_gold_type").toString());
                            tv2.setText(R.string.Days_of_actual_earnings);
                            tvCurrency.setText(objectPagebean.get("sjsy_day").toString());
                            tv3.setText(R.string.Strike_price);
                            tvQuantity.setText(objectPagebean.get("zx_usdt").toString());
                            tv4.setText(R.string.Execute_contract_price_countdown);
                            LogUtils.i("===========" + TimeUtils.millis2String(Long.parseLong(objectPagebean.get("hour24").toString())));
                            long p = Long.parseLong(objectPagebean.get("zx_gold_time").toString()) * 1000;
                            LogUtils.i("=============" + p);
                            CountDown countDown1 = new CountDown(tvTime, p - TimeUtils.getNowMills(), 0);
                            countDown1.timerStart();
                            tvTime.setText(objectPagebean.get("yhzx_cur_time").toString());
                            Rl1.setVisibility(View.VISIBLE);
                            tv5.setText(R.string.Quantity_of_executed_contract_currency);

                            tvPayment.setText(StringUtils.calculateProfit(Double.parseDouble(objectPagebean.get("zx_gold_miney").toString()), 3));
                            Rl2.setVisibility(View.VISIBLE);
                            tv6.setText(R.string.Appointment_success_time);
                            tvTime0.setText(objectPagebean.get("pre_cur_time").toString());
                            Rl3.setVisibility(View.VISIBLE);
                            tv7.setText(R.string.Matching_success_time);
                            tvTime1.setText(objectPagebean.get("pp_cur_time").toString());
                            publicButton.setText(R.string.To_perform_the_contract);
                            tvO.setVisibility(View.VISIBLE);
                        } else if (objectPagebean.get("state").toString().equals("3") || objectPagebean.get("state").toString().equals("4") || objectPagebean.get("state").toString().equals("5")) {
                            Rl.setVisibility(View.VISIBLE);
                            RlNo.setVisibility(View.GONE);
                        }
                        tvMoney.setText(objectPagebean.get("money").toString() + getString(R.string.USDT));


                    } else {
                        Rl.setVisibility(View.VISIBLE);
                        RlNo.setVisibility(View.GONE);

                    }


                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initView() {

        publicButton.setText(R.string.Confirm_an_appointment);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestDatas();


            }
        });
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//
//            BarUtils.setStatusBarAlpha(getActivity(), 0);
//            StatusBarUtils.with(getActivity())
//                    .setColor(getResources().getColor(R.color.black))
//                    .init();
//
//        }
//
//    }


    @Override
    protected int setContentView() {
        return R.layout.fragment_contract;
    }


    @OnClick({R.id.public_button1, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_button1:
                ActivityUtils.startActivity(AppointmentcontractActivity.class);


                break;
            case R.id.public_button:
                PayDialog payDialog = new PayDialog(context) {
                    @Override
                    public void clickCallBack(String str) {
                        pay_pass = str;
                        if (!StringUtils.isEmpty(pay_pass)) {
                            requestDatas2();
                        } else {
                            ToastUtils.showShort(R.string.phoenumber_payment_code_ok);
                        }

                    }

                    @Override
                    public void clickBack() {

                    }
                };
                payDialog.getWindow().setLayout(ScreenUtils.getScreenWidth() - 200, LinearLayout.LayoutParams.WRAP_CONTENT);
                payDialog.setCancelable(false);
                payDialog.setView(new EditText(context));
                payDialog.show();
                break;

            default:
        }
    }

    private String url;

    @Override
    public void requestDatas2() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            if (!oid.equals("")) {
                jsonObject.put("oid", oid);
                url = Constant.paycontract;
            } else {
                url = Constant.tocontractok;
            }

            jsonObject.put("pay_pass", pay_pass);


            TaskPresenterUntils.lifeful(url, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======newcontract======" + success);
                    //adapterRealize = new AdapterRealize();

                    Map<String, Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount(getActivity(), success, null, false).getStringMap();
                    LogUtils.i("======newcontract======" + objectPagebean.get(KeyValueConstants.MSG));
                    if (objectPagebean.get(KeyValueConstants.CODE).equals("200")) {
                        oid = "";
                        requestDatas();
                    }
                    ToastUtils.showShort(objectPagebean.get(KeyValueConstants.MSG).toString());


                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
