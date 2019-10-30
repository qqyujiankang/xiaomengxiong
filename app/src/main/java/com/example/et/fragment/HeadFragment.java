package com.example.et.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;

import com.bumptech.glide.Glide;
import com.example.et.Activity.AppointmentcontractActivity;
import com.example.et.Activity.Contentoftheannouncementctivity;
import com.example.et.Activity.ContractrecordActivity;
import com.example.et.Activity.LoginActivity;
import com.example.et.Activity.ToperformthecontractActivity;
import com.example.et.Adapter.AutoPollAdapter;
import com.example.et.Adapter.AutoPollRecyclerView;
import com.example.et.Adapter.ListActivityAdapter;
import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.ListDatasUtils;
import com.example.et.View.MyGridView;
import com.example.et.entnty.ListObject;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.StringUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.constant.KeyValueConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.ParseUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HeadFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.public_grid_view)
    MyGridView publicGridView;
    @BindView(R.id.recycleView)
    AutoPollRecyclerView recycleView;
    @BindView(R.id.service)
    ImageView service;
    @BindView(R.id.ll_announcement)
    LinearLayout llAnnouncement;
    @BindView(R.id.tv_r_gold)
    TextView tvRGold;
    @BindView(R.id.tv_r_huilv)
    TextView tvRHuilv;
    @BindView(R.id.tv_r_zd)
    TextView tvRZd;
    private Context context;
    Unbinder unbinder;

    private Lifeful lifeful;
    Integer[] images = {R.mipmap.bank_01, R.mipmap.bank_02, R.mipmap.bank_03};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.fragment_head, null);
        context = getActivity();
        lifeful = (Lifeful) getActivity();
        View view = inflater.inflate(R.layout.fragment_head, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        LogUtils.i("=====onCreateView===" + "HeadFragment");
        return view;
    }

    @Override
    public void initView() {
        super.initView();
        ImmersionBar.with(this)
                .statusBarColor(R.color.black)
                .fitsSystemWindows(true)  //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
                .init();
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        banner.setBannerStyle(Banner.NOT_INDICATOR);
        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        banner.setIndicatorGravity(Banner.CENTER);
        //自定义图片加载框架
        banner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Glide.with(getActivity()).load(url).into(view);
            }
        });

        publicGridView.setNumColumns(3);
        publicGridView.setAdapter(new ListActivityAdapter(context, ListDatasUtils.getListActivity(context), null));
        publicGridView.setOnItemClickListener(this);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; ) {
            list.add(" Item: " + ++i);
        }
        AutoPollAdapter adapter = new AutoPollAdapter(list);
        recycleView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recycleView.setAdapter(adapter);
        recycleView.start();

        requestDatas();

    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            LogUtils.i("exp======home===" + jsonObject + "==========");
            TaskPresenterUntils.lifeful(Constant.home, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("homne=========" + success);
                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, true).getStringMap();
                    LogUtils.i("homne=========" + success + "==========" + resultMap.get(KeyValueConstants.MSG));
                    if (resultMap.get(KeyValueConstants.CODE).equals("200")) {
                        tvRGold.setText(CacheUtils.getInstance().getString(CacheConstants.s_gold));
                         double s_huil = Double.parseDouble(CacheUtils.getInstance().getString(CacheConstants.s_huil));


                        double s_zd = Double.parseDouble(CacheUtils.getInstance().getString(CacheConstants.s_zd));
                        if (s_huil > 0.0) {//你输入的是正数

                            tvRHuilv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        } else if (s_huil < 0.0) {//你输入的是负数
                            tvRHuilv.setTextColor(getResources().getColor(R.color.colorAccent_01));
                        }
                        tvRHuilv.setText(StringUtils.calculateProfit(s_huil));
                        tvRZd.setText(StringUtils.calculateProfit(s_zd));
                    } else {
                        ActivityUtils.startActivity(LoginActivity.class);
                        ((Activity) context).finish();
                    }

                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != recycleView) {
            recycleView.stop();
        }
    }

    @OnClick({R.id.service, R.id.ll_announcement})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.service:

                break;
            case R.id.ll_announcement:

                intent.setClass(context, Contentoftheannouncementctivity.class);

                ActivityUtils.startActivity(intent);
                break;
        }
    }


    /**
     * MyGridView点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        ManagerAdapter managerAdapter = null;
        if (parent.getAdapter() instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
            managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
        } else if (parent.getAdapter() instanceof ManagerAdapter) {
            managerAdapter = (ManagerAdapter) parent.getAdapter();
        }

        ListObject objectList = (ListObject) managerAdapter.getItem(position);
        Intent intent = new Intent();
        if (objectList.getName().equals(context.getString(R.string.Appointment_contract))) {
            intent.setClass(context, AppointmentcontractActivity.class);//ToperformthecontractActivity
        } else if (objectList.getName().equals(context.getString(R.string.To_perform_the_contract))) {
            intent.setClass(context, ToperformthecontractActivity.class);//ContractrecordActivity
        } else if (objectList.getName().equals(context.getString(R.string.Contract_record))) {
            intent.setClass(context, ContractrecordActivity.class);//ContractrecordActivity
        }
        ActivityUtils.startActivity(intent);
    }
}
