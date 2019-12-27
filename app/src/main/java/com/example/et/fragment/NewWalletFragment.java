package com.example.et.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.TextView;

import com.example.et.Activity.PersonalAssetsActivity;
import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Adapter.NewAssAdaper;
import com.example.et.Adapter.NewAssAdaper1;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.View.MyListView;
import com.example.et.entnty.Assetswallet;
import com.example.et.entnty.Pagebean;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.PublicSwipeRefreshLayout.SwipeRefreshLayout;
import com.example.et.util.StringUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.AdapterRealize;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewWalletFragment extends BaseFragment {


    @BindView(R.id.tv_personal)
    TextView tvPersonal;
    @BindView(R.id.listv)
    MyListView listv;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.list_item)
    MyListView listItem;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_04)
    TextView tv04;
    @BindView(R.id.tv_05)
    TextView tv05;
    @BindView(R.id.tv_frozen)
    TextView tvFrozen;
    @BindView(R.id.tv_06)
    TextView tv06;
    @BindView(R.id.list_frozen)
    MyListView listFrozen;
    private Context context;
    private AdapterRealize adapterRealize;

    private Lifeful lifeful;

    @Override
    protected int setContentView() {
        return R.layout.fragment_new_wallet;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifeful = (Lifeful) getActivity();
        context = getActivity();
    }

    private Assetswallet assetswallet;
    private Assetswallet assetswallet1;

    @Override
    public void initView() {
        super.initView();

        requestDatas();
        requestDatas2();
        requestDatas3();
        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                ManagerAdapter managerAdapter = null;
                if (parent.getAdapter() instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
                    managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
                } else if (parent.getAdapter() instanceof ManagerAdapter) {
                    managerAdapter = (ManagerAdapter) parent.getAdapter();
                }
                assetswallet = (Assetswallet) managerAdapter.getItem(i);
                Intent intent = new Intent();
                intent.setClass(context, PersonalAssetsActivity.class);
                intent.putExtra("assetswallet", assetswallet);
                intent.putExtra("name", getString(R.string.things_personal));
                ActivityUtils.startActivity(intent);
            }
        });
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                ManagerAdapter managerAdapter = null;
                if (parent.getAdapter() instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
                    managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
                } else if (parent.getAdapter() instanceof ManagerAdapter) {
                    managerAdapter = (ManagerAdapter) parent.getAdapter();
                }
                assetswallet1 = (Assetswallet) managerAdapter.getItem(i);
                Intent intent = new Intent();
                intent.setClass(context, PersonalAssetsActivity.class);
                intent.putExtra("assetswallet", assetswallet1);
                intent.putExtra("name", getString(R.string.Reference_Asset));
                ActivityUtils.startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestDatas();
                requestDatas2();
                requestDatas3();
            }
        });
    }
   List<Object> objectList=new ArrayList<>();
    @Override
    public void requestDatas3() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("type", "dongjie");


            TaskPresenterUntils.lifeful(Constant.assetswallet, jsonObject, new OnLoadLifefulListener<String>(swipeRefreshLayout, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("============dongjie==" + success);

                    adapterRealize = new AdapterRealize();


                    Pagebean<Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, Assetswallet[].class, false);
                    newAssAdapers1 = ((ArrayList<Assetswallet>) (ArrayList) objectPagebean.getList());

//                    for (int i = 0; i < newAssAdapers1.size(); i++) {
//                        newAssAdapers1.get(i).setType("dongjie");
////                        if (newAssAdapers1.get(i).getName().equals("USDT")) {
////                            tvFrozen.setText(StringUtils.calculateProfit(Double.valueOf(newAssAdapers1.get(i).getNumber()), 5));
////                        }
//                    }
//                    objectList.add(newAssAdapers1);
//                    objectPagebean.setList(objectList);
                    adapterRealize.AdapterSetListDatas(objectPagebean, 0, listFrozen, context, NewAssAdaper1.class, lifeful);//返回第几页


                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String personal;

    @Override
    public void requestDatas2() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("type", "contract");


            TaskPresenterUntils.lifeful(Constant.assetswallet, jsonObject, new OnLoadLifefulListener<String>(swipeRefreshLayout, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======钱包======contract==" + success);

                    adapterRealize = new AdapterRealize();


                    Pagebean<Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, Assetswallet[].class, false);
                    newAssAdapers1 = ((ArrayList<Assetswallet>) (ArrayList) objectPagebean.getList());
                    for (int i = 0; i < newAssAdapers1.size(); i++) {
                        if (newAssAdapers1.get(i).getName().equals("USDT")) {
                            tvContent.setText(StringUtils.calculateProfit(Double.valueOf(newAssAdapers1.get(i).getNumber()), 5));
                        }
                    }
                    adapterRealize.AdapterSetListDatas(objectPagebean, 0, listItem, context, NewAssAdaper.class, lifeful);//返回第几页


                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        requestDatas();
        requestDatas2();
        requestDatas3();
    }

    private List<Assetswallet> newAssAdapers = new ArrayList<>();
    private List<Assetswallet> newAssAdapers1 = new ArrayList<>();

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("type", "all");


            TaskPresenterUntils.lifeful(Constant.assetswallet, jsonObject, new OnLoadLifefulListener<String>(swipeRefreshLayout, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======钱包======all====" + success);

                    adapterRealize = new AdapterRealize();
                    Pagebean<Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, Assetswallet[].class, false);
                    newAssAdapers = ((ArrayList<Assetswallet>) (ArrayList) objectPagebean.getList());
                    for (int i = 0; i < newAssAdapers.size(); i++) {
                        if (newAssAdapers.get(i).getName().equals("USDT")) {
                            tvPersonal.setText(StringUtils.calculateProfit(Double.valueOf(newAssAdapers.get(i).getNumber()), 5));
                        }
                    }
                    adapterRealize.AdapterSetListDatas(objectPagebean, 0, listv, context, NewAssAdaper.class, lifeful);//返回第几页
                    LogUtils.i("personal====" + success);


                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
