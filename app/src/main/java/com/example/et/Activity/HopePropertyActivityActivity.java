package com.example.et.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.entnty.Ass;
import com.example.et.util.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Hope
 */
public class HopePropertyActivityActivity extends BaseActivity {

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
    @BindView(R.id.Rl_banl)
    TextView RlBanl;
    @BindView(R.id.Rl_ban2)
    RelativeLayout RlBan2;
    @BindView(R.id.RL_Hope)
    RelativeLayout RLHope;
    @BindView(R.id.btn_top_up)
    Button btnTopUp;
    @BindView(R.id.btn_Mention_money)
    Button btnMentionMoney;
    @BindView(R.id.btn_transfer)
    Button btnTransfer;
    @BindView(R.id.btn_record)
    Button btnRecord;
    @BindView(R.id.tv_rental)
    TextView tvRental;
    @BindView(R.id.tv_usable)
    TextView tvUsable;
    @BindView(R.id.tv_convert)
    TextView tvConvert;
    private Context context;
    Ass ass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hope_property_activity);
        ButterKnife.bind(this);
        getIntentDatas();
        initView();
        // requestDatas();

    }


    @Override
    public void getIntentDatas() {
        super.getIntentDatas();
        ass = getIntent().getParcelableExtra("ass");

        if (ass.getId() == 8) {
            btnTransfer.setVisibility(View.GONE);
        }
        RlBanl.setText(ass.getName());
        tvRental.setText(StringUtils.calculateProfit(Double.valueOf(ass.getNumber())));
        tvUsable.setText(StringUtils.calculateProfit(Double.valueOf(ass.getNewnumber())));
        tvConvert.setText(StringUtils.calculateProfit(Double.valueOf(ass.getCny())));

    }


    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(ass.getName() + getString(R.string.HOPE_the_asset));
    }

    @OnClick({R.id.public_back, R.id.public_other, R.id.btn_top_up, R.id.btn_Mention_money, R.id.btn_transfer, R.id.btn_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.public_other:
                break;
            case R.id.btn_top_up:
                break;
            case R.id.btn_Mention_money:
                break;
            case R.id.btn_transfer:
                ActivityUtils.startActivity(TransferActivity.class);
                break;
            case R.id.btn_record:
                break;
        }
    }
}
