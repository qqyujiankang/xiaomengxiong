package com.example.et.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hope_property_activity);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.HOPE_the_asset));
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
