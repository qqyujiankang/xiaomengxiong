package com.example.et.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

import com.example.et.Activity.HopePropertyActivityActivity;
import com.example.et.Adapter.AssAdaper;
import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.entnty.Ass;
import com.example.et.util.CacheUtils;
import com.example.et.util.JsonUtil;
import com.example.et.util.LogUtils;
import com.example.et.util.PublicSwipeRefreshLayout.SwipeRefreshLayout;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.constant.KeyValueConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.AdapterRealize;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 钱包
 */
public class WalletFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    @BindView(R.id.listv)
    ListView listv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private Context context;


    private Lifeful lifeful;

    public static boolean istype = false;
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            BarUtils.setStatusBarAlpha(getActivity(), 0);
//            // BarUtils.addMarginTopEqualStatusBarHeight(swipeRefreshLayout);// 其实这个只需要调用一次即可
//            StatusBarUtils.with(getActivity())
//                    .setColor(getResources().getColor(R.color.black))
//                    .init();
//        }
//
//
//    }

    private AdapterRealize adapterRealize;

    @Override
    public void onResume() {
        super.onResume();
        if (istype) {
            requestDatas();
        }
    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));


            TaskPresenterUntils.lifeful(Constant.assets, jsonObject, new OnLoadLifefulListener<String>(swipeRefreshLayout, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======钱包======" + success);
                    adapterRealize = new AdapterRealize();

                    Map<String, Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount(getActivity(), success, null, false).getStringMap();
                    if (objectPagebean.get(KeyValueConstants.CODE).equals("200")) {
                        if (objectPagebean.containsKey("ass")) {
                            List<Ass> asses = JsonUtil.stringToList(objectPagebean.get("ass").toString(), Ass.class);
                            listv.setAdapter(new AssAdaper(context, asses, null));
                        }
                    }


                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifeful = (Lifeful) getActivity();
        context = getActivity();
    }

    @Override
    public void initView() {
        super.initView();
        requestDatas();

        listv.setOnItemClickListener(this);
//         swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                requestDatas();
//                requestDatas3();
//                requestDatas2();
//
//
//            }
//        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestDatas();
            }
        });


    }


    @Override
    protected int setContentView() {
        return R.layout.fragmnet_wallet;
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
        intent.putExtra("ass", ass);
        intent.setClass(context, HopePropertyActivityActivity.class);
        ActivityUtils.startActivity(intent);
    }

}
