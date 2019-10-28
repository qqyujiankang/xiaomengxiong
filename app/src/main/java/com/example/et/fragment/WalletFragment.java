package com.example.et.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Activity.HopePropertyActivityActivity;
import com.example.et.Activity.USTDPropertyActivity;
import com.example.et.Adapter.AssAdaper;
import com.example.et.Adapter.ContractAdapter;
import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.entnty.Ass;
import com.example.et.entnty.Contract;
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
import butterknife.Unbinder;

/**
 * 钱包
 */
public class WalletFragment extends BaseFragment implements AdapterView.OnItemClickListener {


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
    @BindView(R.id.listv)
    ListView listv;
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
        requestDatas();
        return view;
    }

    private AdapterRealize adapterRealize;

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));


            TaskPresenterUntils.lifeful(Constant.assets, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======钱包======" + success);
                    adapterRealize = new AdapterRealize();

                    Pagebean<Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount1(getActivity(), success, Ass[].class, false);

                    adapterRealize.AdapterSetListDatas(objectPagebean, 0, listv, context, AssAdaper.class, lifeful);//返回第几页


                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        listv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        ManagerAdapter managerAdapter = null;
        if (parent.getAdapter() instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
            managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
        } else if (parent.getAdapter() instanceof ManagerAdapter) {
            managerAdapter = (ManagerAdapter) parent.getAdapter();
        }
        Intent intent = new Intent();
        Ass ass = (Ass) managerAdapter.getItem(i);

        if (ass.getId() == 8) {
            intent.setClass(context, USTDPropertyActivity.class);

        } else if (ass.getId() == 7) {
            intent.setClass(context, HopePropertyActivityActivity.class);
        }
        ActivityUtils.startActivity(intent);
    }
}
