package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.GainRecordingAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.entnty.GainRecording;
import com.example.et.entnty.Pagebean;
import com.example.et.util.CacheUtils;
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

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收益记录
 */
public class GainRecordingActivity extends BaseActivity {

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
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.refreshView)
    RelativeLayout refreshView;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_jiangtaishuyi)
    TextView tvJiangtaishuyi;
    @BindView(R.id.tv_dogntai)
    TextView tvDogntai;
    @BindView(R.id.tv_jiedian)
    TextView tvJiedian;
    @BindView(R.id.tv_heyuechentgb)
    TextView tvHeyuechentgb;
    @BindView(R.id.tv_chaojia)
    TextView tvChaojia;
    @BindView(R.id.tv_tuijainjiangtaishuyi)
    TextView tvTuijainjiangtaishuyi;
    @BindView(R.id.ll_a)
    LinearLayout llA;
    @BindView(R.id._r1)
    RelativeLayout R1;
    @BindView(R.id._r2)
    RelativeLayout R2;
    @BindView(R.id._r3)
    RelativeLayout R3;
    @BindView(R.id._r4)
    RelativeLayout R4;
    @BindView(R.id._r5)
    RelativeLayout R5;
    @BindView(R.id._r6)
    RelativeLayout R6;
    private AdapterRealize adapterRealize;
    private Context context;
    private Lifeful lifeful;
    private int id;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = GainRecordingActivity.this;
        lifeful = GainRecordingActivity.this;
        setContentView(R.layout.activity_gain_recording);
        ButterKnife.bind(this);
        initView();
        requestDatas();

    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.gain_recording));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                requestDatas2();

            }
        });

        swipeRefreshLayout.setOnLoadMoreListener(new SwipeRefreshLayout.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                requestDatas2();


            }
        });
    }

    Pagebean<Object> objectPagebean;
    private String url;

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("page", page);

            url = Constant.profit;


            TaskPresenterUntils.lifeful(url, jsonObject, new OnLoadLifefulListener<String>(swipeRefreshLayout, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {


                    if (adapterRealize == null) {
                        adapterRealize = new AdapterRealize();
                    }

                    objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, GainRecording[].class, true);
                    if (objectPagebean.getList().size() != 0) {
                        llA.setVisibility(View.GONE);
                        ll.setVisibility(View.VISIBLE);
                        page = adapterRealize.AdapterSetListDatas(objectPagebean, page, listView, context, GainRecordingAdapter.class, lifeful);//返回第几页
                    } else {
                           llA.setVisibility(View.VISIBLE);
                        ll.setVisibility(View.GONE);
                        requestDatas2();
                    }

                }


            }, this));
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

            url = Constant.assetsprders;


            TaskPresenterUntils.lifeful(url, jsonObject, new OnLoadLifefulListener<String>(swipeRefreshLayout, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {

                    Map<String, Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();
                    if (objectPagebean.get("code").equals("200")) {

                        if (!objectPagebean.get("chaojijiedian").toString().equals("0")) {
                            tvChaojia.setText(StringUtils.calculateProfit(Double.parseDouble(objectPagebean.get("chaojijiedian").toString()), 5));
                        } else {
                            R1.setVisibility(View.GONE);

                        }
                        if (!objectPagebean.get("jingtai").toString().equals("0")) {
                            tvJiangtaishuyi.setText(StringUtils.calculateProfit(Double.parseDouble(objectPagebean.get("jingtai").toString()), 5));
                        } else {
                            R2.setVisibility(View.GONE);

                        }
                        if (!objectPagebean.get("dongtai").toString().equals("0")) {
                            tvDogntai.setText(StringUtils.calculateProfit(Double.parseDouble(objectPagebean.get("dongtai").toString()), 5));
                        } else {
                            R3.setVisibility(View.GONE);

                        }
                        if (!objectPagebean.get("jiedian").toString().equals("0")) {
                            tvJiedian.setText(StringUtils.calculateProfit(Double.parseDouble(objectPagebean.get("jiedian").toString()), 5));
                        } else {
                            R4.setVisibility(View.GONE);

                        }

                        if (!objectPagebean.get("heyue").toString().equals("0")) {
                            tvHeyuechentgb.setText(StringUtils.calculateProfit(Double.parseDouble(objectPagebean.get("heyue").toString()), 5));
                        } else {
                            R5.setVisibility(View.GONE);
                            tvHeyuechentgb.setText("0");
                        }

                        if (!objectPagebean.get("tuijianchaojijiedian").toString().equals("0")) {
                            tvTuijainjiangtaishuyi.setText(StringUtils.calculateProfit(Double.parseDouble(objectPagebean.get("tuijianchaojijiedian").toString()), 5));
                        } else {
                            R6.setVisibility(View.GONE);
                            tvTuijainjiangtaishuyi.setText("0");
                        }


                    }


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
