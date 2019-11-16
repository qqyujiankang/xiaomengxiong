package com.example.et.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.bumptech.glide.Glide;
import com.example.et.Activity.AppointmentcontractActivity;
import com.example.et.Activity.Contentoftheannouncementctivity;
import com.example.et.Activity.ContractrecordActivity;
import com.example.et.Activity.LoginActivity;
import com.example.et.Activity.MainActivity;
import com.example.et.Activity.ToperformthecontractActivity;
import com.example.et.Adapter.AutoPollAdapter;
import com.example.et.Adapter.AutoPollRecyclerView;
import com.example.et.Adapter.ListActivityAdapter;
import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.GsonUtil;
import com.example.et.Ustlis.ListDatasUtils;
import com.example.et.Ustlis.ScreenUtils;
import com.example.et.Ustlis.StatusBarUtils;
import com.example.et.View.GridSpacingItemDecoration;
import com.example.et.View.MyDiog;
import com.example.et.View.MyGridView;
import com.example.et.entnty.DataFlow;
import com.example.et.entnty.ListObject;
import com.example.et.entnty.Pagebean;
import com.example.et.util.BarUtils;
import com.example.et.util.CacheUtils;
import com.example.et.util.FragmentUtils;
import com.example.et.util.JsonUtil;
import com.example.et.util.LogUtils;
import com.example.et.util.PublicSwipeRefreshLayout.SwipeRefreshLayout;
import com.example.et.util.StringUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.constant.KeyValueConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.ParseUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
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
    ImageView textView2;
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
    @BindView(R.id.tv_s_gold)
    TextView tvSGold;
    @BindView(R.id.tv_s_huil)
    TextView tvSHuil;
    @BindView(R.id.tv_s_zd)
    TextView tvSZd;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_new_name)
    TextView tvNewName;
    private Context context;
    Unbinder unbinder;

    private Lifeful lifeful;
    Integer[] images = {R.mipmap.bank_01, R.mipmap.bank_02, R.mipmap.bank_03};
    private OnButtonClick onButtonClick;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        lifeful = (Lifeful) getActivity();
    }

    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    public interface OnButtonClick {
        public void onClick(View view);
    }

    @Override
    public void initView() {
        super.initView();
//        ImmersionBar.with(this)
//                .statusBarColor(R.color.black)
//                .fitsSystemWindows(true)  //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
//                .init();

        requestDatas();
        requestDatas2();
        requestDatas3();
        requestDatas4();

//        StatusBarUtils.with(getActivity())
//                .setColor(getResources().getColor(R.color.black))
//                .init();

        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
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


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestDatas();
                requestDatas3();
                requestDatas2();


            }
        });


    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            BarUtils.setStatusBarAlpha(getActivity(), 0);
//            StatusBarUtils.with(getActivity())
//                    .setColor(getResources().getColor(R.color.black))
//                    .init();
//        }
//
//
//    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_head;
    }

    public static boolean d;

    @Override
    public void requestDatas3() {
        super.requestDatas3();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            TaskPresenterUntils.lifeful(Constant.todaybaifenbi, jsonObject, new OnLoadLifefulListener<String>(swipeRefreshLayout, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("homne====shujuliu=====" + success);
                    // HashMap<String,Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();
                    Map<String, Object> map = GsonUtil.GsonToMaps(success);

                    if (map.get("code").toString().equals("200.0")) {
                        if (d == false) {
//                            MyDiog myDiog = new MyDiog(context, map.get("data").toString(), 0);
//                            myDiog.show();
                            // d = true;
                            MyDiog myDiog = new MyDiog(context, map.get("data").toString(), 0);
                            myDiog.show();
                            myDiog.setCanceledOnTouchOutside(false);
                            myDiog.getWindow().setLayout(ScreenUtils.getScreenWidth() - 100, LinearLayout.LayoutParams.WRAP_CONTENT);
                        }

                    }

                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void requestDatas2() {
        super.requestDatas2();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            TaskPresenterUntils.lifeful(Constant.shujuliu, jsonObject, new OnLoadLifefulListener<String>(swipeRefreshLayout, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("homne====shujuliu=====" + success);
                    Pagebean<Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, DataFlow[].class, false);

                    if (objectPagebean.getStringMap().get(KeyValueConstants.CODE).equals("200")) {
                        AutoPollAdapter adapter = new AutoPollAdapter((ArrayList<DataFlow>) (ArrayList) objectPagebean.getList());
                        //recycleView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                        // recycleView.addItemDecoration(new GridSpacingItemDecoration(1, 10, false));
                        //   recycleView.setBackground(context.getResources().getColor(R.color.white));
                        recycleView.setAdapter(adapter);

                        recycleView.start();
                    }

                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            LogUtils.i("exp======home===" + jsonObject + "==========");
            TaskPresenterUntils.lifeful(Constant.home, jsonObject, new OnLoadLifefulListener<String>(swipeRefreshLayout, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("homne=========" + success);
                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, true).getStringMap();
                    LogUtils.i("homne=========" + success + "==========" + resultMap.get(KeyValueConstants.MSG));
                    if (resultMap.get(KeyValueConstants.CODE).equals("200")) {
                        if (!CacheUtils.getInstance().getString(CacheConstants.r_huil).equals("")) {
                            tvRGold.setText(CacheUtils.getInstance().getString(CacheConstants.r_gold));
                            tvRHuilv.setText(StringUtils.calculateProfit(Double.parseDouble(CacheUtils.getInstance().getString(CacheConstants.r_huil)), 5));
                            double r_zd = Double.parseDouble(CacheUtils.getInstance().getString(CacheConstants.r_zd));
                            //     double r_zd = 5454.5454534;

                            if (r_zd > 0) {//你输入的是正数
                                tvRZd.setText("+" + StringUtils.calculateProfit1(r_zd) + getString(R.string.ts1));
                                tvRZd.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            } else if (r_zd < 0) {//你输入的是负数
                                tvRZd.setText(StringUtils.calculateProfit1(r_zd) + getString(R.string.ts1));
                                tvRZd.setTextColor(getResources().getColor(R.color.colorAccent_01));
                            }
                            tvSGold.setText(CacheUtils.getInstance().getString(CacheConstants.s_gold));
                            tvSHuil.setText(StringUtils.calculateProfit(Double.parseDouble(CacheUtils.getInstance().getString(CacheConstants.s_huil)), 5));
                            double s_zd = Double.parseDouble(CacheUtils.getInstance().getString(CacheConstants.s_zd));

                            if (s_zd > 0) {//你输入的是正数
                                tvSZd.setText("+" + StringUtils.calculateProfit1(s_zd) + getString(R.string.ts1));
                                tvSZd.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            } else if (s_zd < 0) {//你输入的是负数
                                tvSZd.setText(StringUtils.calculateProfit1(s_zd) + getString(R.string.ts1));
                                tvSZd.setTextColor(getResources().getColor(R.color.colorAccent_01));
                            }

                            LogUtils.i("homne======1===" + CacheUtils.getInstance().getString(CacheConstants.newd));
                            Map<String, Object> stringObjectMap;
                            stringObjectMap = JsonUtil.parseJSON(CacheUtils.getInstance().getString(CacheConstants.newd));
                            tvNewName.setText(stringObjectMap.get("name").toString());
                        }

                    } else {
//                        ActivityUtils.startActivity(LoginActivity.class);
//                        ((Activity) context).finish();
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
                Intent intent2 = new MQIntentBuilder(getActivity()).build();
                startActivity(intent2);

                break;
            case R.id.ll_announcement:

                intent.setClass(context, Contentoftheannouncementctivity.class);

                ActivityUtils.startActivity(intent);
                break;
            default:
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
            if (state.equals("")) {
                intent.setClass(context, AppointmentcontractActivity.class);//ToperformthecontractActivity
            } else {
                //onButtonClick.onClick(view);
                intent.setClass(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("fragment_flag", 2);
                startActivity(intent);
            }

        } else if (objectList.getName().equals(context.getString(R.string.To_perform_the_contract))) {
            intent.setClass(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("fragment_flag", 2);
            startActivity(intent);
            // intent.setClass(context, ToperformthecontractActivity.class);//ContractrecordActivity
        } else if (objectList.getName().equals(context.getString(R.string.Contract_record))) {
            intent.setClass(context, ContractrecordActivity.class);//ContractrecordActivity
        }
        ActivityUtils.startActivity(intent);
    }

    private String state = "1";

    @Override
    public void requestDatas4() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));

            TaskPresenterUntils.lifeful(Constant.newcontract, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======newcontract======" + success);
                    //adapterRealize = new AdapterRealize();

                    Map<String, Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();
                    LogUtils.i("======newcontract======" + objectPagebean.get(KeyValueConstants.MSG));
                    if (objectPagebean.get(KeyValueConstants.CODE).equals("200")) {
                        state = objectPagebean.get("state").toString();


                    } else {
                        state = "";
                    }


                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
