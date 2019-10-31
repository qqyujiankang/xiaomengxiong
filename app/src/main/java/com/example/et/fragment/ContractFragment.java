package com.example.et.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.et.Constant;
import com.example.et.R;
import com.example.et.util.CacheUtils;
import com.example.et.util.CountDown;
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
import butterknife.Unbinder;

/**
 * 合约
 */

public class ContractFragment extends BaseFragment {


    @BindView(R.id.tv_not_available)
    TextView tvNotAvailable;
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
    private Context context;
    Unbinder unbinder;

    private Lifeful lifeful;

    @Override
    public void onResume() {
        super.onResume();
        initView();
        requestDatas();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        lifeful = (Lifeful) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_contract, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        LogUtils.i("=====onCreateView===" + "ContractFragment");

        return view;
    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));


            TaskPresenterUntils.lifeful(Constant.newcontract, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======newcontract======" + success);
                    //adapterRealize = new AdapterRealize();

                    Map<String, Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount(getActivity(), success, null, false).getStringMap();

                    if (objectPagebean.get(KeyValueConstants.CODE).equals("200")) {
                          LogUtils.i("======newcontract======" + objectPagebean.get("state").toString());
                        if (objectPagebean.get("state").toString().equals("0")) {//预约中
                            tvState.setText(getString(R.string.To_make_an_appointment_in));
                            // getCountDownTime(Long.parseLong(objectPagebean.get("yytime_daojishi").toString()));
                            CountDown countDown = new CountDown(tvTime, Long.parseLong(objectPagebean.get("yytime_daojishi").toString()));
                            countDown.timerStart();
                        }
                        tvPrice.setText(objectPagebean.get("pre_cur_money").toString());
                        tvCurrency.setText(objectPagebean.get("pre_cur").toString());
                        tvQuantity.setText(objectPagebean.get("pre_cur_number").toString());
                        tvTime.setText(objectPagebean.get("yytime").toString());
                    }


                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {

        publicButton.setText("确认预约");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
