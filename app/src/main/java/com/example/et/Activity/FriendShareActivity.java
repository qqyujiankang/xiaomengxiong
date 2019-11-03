package com.example.et.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.R;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分享好友
 */
public class FriendShareActivity extends BaseActivity {

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


    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.mldz_chufanginfo_weblayout)
    LinearLayout mldzChufanginfoWeblayout;

    private View view;
    AgentWeb agentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_share);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(R.string.Friends_share);
        agentWeb = AgentWeb.with(FriendShareActivity.this)//传入Activity
                .setAgentWebParent(mldzChufanginfoWeblayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .createAgentWeb()//
                .ready()
                .go("http://e.firefoxchina.cn/");

    }

    @OnClick(R.id.public_back)
    public void onViewClicked() {
        finish();
    }
}
