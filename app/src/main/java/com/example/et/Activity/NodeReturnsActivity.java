package com.example.et.Activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.NodeReturnsAdapter;
import com.example.et.R;
import com.example.et.entnty.NodeReturns;
import com.example.et.util.CacheUtils;
import com.example.et.util.constant.CacheConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 节点收益
 */
public class NodeReturnsActivity extends BaseActivity {

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
    @BindView(R.id.list_item)
    ListView listItem;
    private List<NodeReturns> nodeReturns = new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_node_returns);
        ButterKnife.bind(this);
        initView();
        requestDatas();

    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        nodeReturns.add(new NodeReturns(getString(R.string.Revenue_generated), CacheUtils.getInstance().getString(CacheConstants.ycs_profit), CacheUtils.getInstance().getString(CacheConstants.ycs_profit_cny)));
        nodeReturns.add(new NodeReturns(getString(R.string.Revenue_being_booked), CacheUtils.getInstance().getString(CacheConstants.yyz_profit), CacheUtils.getInstance().getString(CacheConstants.yyz_profit_cny)));
        nodeReturns.add(new NodeReturns(getString(R.string.Cancelled_earnings), CacheUtils.getInstance().getString(CacheConstants.yqx_profit), CacheUtils.getInstance().getString(CacheConstants.yqx_profit_cny)));
        listItem.setAdapter(new NodeReturnsAdapter(context, nodeReturns, null));

    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.Node_returns));
    }

    @OnClick(R.id.public_back)
    public void onViewClicked() {
        finish();
    }
}
