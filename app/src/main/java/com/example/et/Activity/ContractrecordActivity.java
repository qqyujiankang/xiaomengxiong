package com.example.et.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.Contractrecordadapetr;
import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.entnty.Contract;
import com.example.et.entnty.Contractrecord;
import com.example.et.entnty.Pagebean;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.AdapterRealize;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 合约记录
 */
public class ContractrecordActivity extends BaseActivity {

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
    @BindView(R.id.lv_contern)
    ListView lvContern;
    Context context;
    private AdapterRealize adapterRealize;
    private Lifeful lifeful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        lifeful = ContractrecordActivity.this;
        setContentView(R.layout.activity_contractrecord);
        ButterKnife.bind(this);
        initView();
        requestDatas();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.Contract_record));
        lvContern.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                ManagerAdapter managerAdapter = null;
                if (parent.getAdapter() instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
                    managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
                } else if (parent.getAdapter() instanceof ManagerAdapter) {
                    managerAdapter = (ManagerAdapter) parent.getAdapter();
                }
                Contractrecord contractrecord = (Contractrecord) managerAdapter.getItem(i);
                Intent intent = new Intent();
                if (contractrecord.getState() == 0 || contractrecord.getState() == 1 || contractrecord.getState() == 2) {
                    intent.setClass(ContractrecordActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("fragment_flag", 2);
                    ActivityUtils.startActivity(intent);
                } else if (contractrecord.getState() == 3 || contractrecord.getState() == 4 || contractrecord.getState() == 5) {
                    intent.setClass(context,TheContractDetailsActivity.class);
                    intent.putExtra("id", contractrecord.getId());
                    ActivityUtils.startActivity(intent);
                }


            }
        });

    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            TaskPresenterUntils.lifeful(Constant.mycontract, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    if (adapterRealize == null) {
                        adapterRealize = new AdapterRealize();
                    }

                    Pagebean<Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount(ContractrecordActivity.this, success, Contractrecord[].class, false);

                    adapterRealize.AdapterSetListDatas(objectPagebean, 0, lvContern, context, Contractrecordadapetr.class, lifeful);//返回第几页


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
