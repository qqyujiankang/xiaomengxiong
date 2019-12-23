package com.example.et.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.ListActivityAdapter;
import com.example.et.Adapter.ListActivitynewAdapter;
import com.example.et.R;
import com.example.et.Ustlis.ListDatasUtils;
import com.example.et.View.MyGridView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 个人资产
 */
public class PersonalAssetsActivity extends BaseActivity {

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
    @BindView(R.id.iv_view)
    ImageView ivView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.RL_Usdt)
    RelativeLayout RLUsdt;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.public_grid_view)
    MyGridView publicGridView;
    private Context context;
    private String name = "HOPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = PersonalAssetsActivity.this;
        setContentView(R.layout.activity_personal_assets);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        if (name.equals("HOPE")) {
            publicGridView.setNumColumns(3);
        } else {
            publicGridView.setNumColumns(5);
        }

        publicGridView.setAdapter(new ListActivitynewAdapter<>(context, ListDatasUtils.getListActivity1(context, "HOPE"), null));
        publicGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
