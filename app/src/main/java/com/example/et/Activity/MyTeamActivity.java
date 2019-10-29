package com.example.et.Activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.MyTeamsAdapter;
import com.example.et.R;
import com.example.et.Ustlis.GsonUtil;
import com.example.et.Ustlis.ImageLoaderUtil;
import com.example.et.View.ListScrollView;
import com.example.et.entnty.MyTeam;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.constant.CacheConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的团队
 */
public class MyTeamActivity extends BaseActivity {

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
    @BindView(R.id.iv_holder)
    ImageView ivHolder;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.Rl_Record)
    RelativeLayout RlRecord;
    @BindView(R.id.tv_team)
    TextView tvTeam;
    @BindView(R.id.tv_Active_member)
    TextView tvActiveMember;
    @BindView(R.id.tv_performance)
    TextView tvPerformance;
    @BindView(R.id.list_item)
    ListView listItem;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_my_team);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getText(R.string.my_team));
        //头像
        ImageLoaderUtil.loadCircleImage(context, CacheUtils.getInstance().getString(CacheConstants.photo_url)
                + CacheUtils.getInstance().getString(CacheConstants.photo), ivHolder);
        tvName.setText(CacheUtils.getInstance().getString(CacheConstants.name));
        tvPhone.setText(CacheUtils.getInstance().getString(CacheConstants.PHONE));
        tvTeam.setText(CacheUtils.getInstance().getString(CacheConstants.team_pp));//团队人数
        tvPerformance.setText(CacheUtils.getInstance().getString(CacheConstants.achievement));//业绩
        tvActiveMember.setText(CacheUtils.getInstance().getString(CacheConstants.team_okpp));//有效人数
        LogUtils.i("========tunadui======" + CacheUtils.getInstance().getString(CacheConstants.team));
        List<MyTeam> myTeams = GsonUtil.jsonToList(CacheUtils.getInstance().getString(CacheConstants.team), MyTeam.class);
        listItem.setAdapter(new MyTeamsAdapter(context, myTeams, null));


    }

    @OnClick(R.id.public_back)
    public void onViewClicked() {
        finish();
    }
}
