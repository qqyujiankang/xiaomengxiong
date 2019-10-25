package com.example.et.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Activity.HopePropertyActivityActivity;
import com.example.et.Activity.USTDPropertyActivity;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.util.lifeful.Lifeful;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 钱包
 */
public class WalletFragment extends BaseFragment {

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
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.RL_Usdt)
    RelativeLayout RLUsdt;
    @BindView(R.id.iv_bank_)
    ImageView ivBank1;
    @BindView(R.id.Rl_banl)
    RelativeLayout RlBanl;
    @BindView(R.id.Rl_ban2)
    RelativeLayout RlBan2;
    @BindView(R.id.RL_Hope)
    RelativeLayout RLHope;
    private Context context;
    Unbinder unbinder;

    private Lifeful lifeful;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        lifeful = (Lifeful) getActivity();
        context = getActivity();
        View view = inflater.inflate(R.layout.fragmnet_wallet, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        super.initView();

        publicBack.setVisibility(View.GONE);
        publicTitleTv.setText(getString(R.string.menu_contact));
    }

    @OnClick({R.id.RL_Usdt, R.id.RL_Hope})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.RL_Usdt:
                intent.setClass(context, USTDPropertyActivity.class);
                ActivityUtils.startActivity(intent);
                break;
            case R.id.RL_Hope:
                intent.setClass(context, HopePropertyActivityActivity.class);
                ActivityUtils.startActivity(intent);
                break;
        }
    }
}
