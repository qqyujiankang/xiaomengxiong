package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.GainRecordingAdapter;
import com.example.et.Adapter.RecordAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.entnty.GainRecording;
import com.example.et.entnty.Pagebean;
import com.example.et.util.CacheUtils;
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
                requestDatas();

            }
        });

        swipeRefreshLayout.setOnLoadMoreListener(new SwipeRefreshLayout.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                requestDatas();


            }
        });
    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("page", page);
            LogUtils.i("收益记录==========" + jsonObject);

            TaskPresenterUntils.lifeful(Constant.profit, jsonObject, new OnLoadLifefulListener<String>(swipeRefreshLayout, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {


                    if (adapterRealize == null) {
                        adapterRealize = new AdapterRealize();
                    }

                    Pagebean<Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, GainRecording[].class, true);
                    LogUtils.i("======钱包======" + success + objectPagebean.getStringMap().get(KeyValueConstants.MSG));
                    page = adapterRealize.AdapterSetListDatas(objectPagebean, page, listView, context, GainRecordingAdapter.class, lifeful);//返回第几页


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
