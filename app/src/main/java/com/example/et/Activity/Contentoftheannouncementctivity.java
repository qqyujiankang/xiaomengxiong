package com.example.et.Activity;

import android.app.Activity;
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

import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Adapter.NoticeAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.entnty.Announcement;
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
 * 公告
 */
public class Contentoftheannouncementctivity extends BaseActivity implements AdapterView.OnItemClickListener {

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
    ListView listv;
    private Context context;
    private Lifeful lifeful;
    private AdapterRealize adapterRealize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        lifeful = this;
        setContentView(R.layout.activity_contentoftheannouncementctivity);
        ButterKnife.bind(this);
        initView();
        requestDatas();
    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            TaskPresenterUntils.lifeful(Constant.news, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("=======lifeful====" + success);
                    Pagebean<Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, Announcement[].class, false);
                    if (adapterRealize == null) {
                        adapterRealize = new AdapterRealize();
                    }
                    adapterRealize.AdapterSetListDatas(objectPagebean, 0, listv, context, NoticeAdapter.class, lifeful);//返回第几页


                }

            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.announcement));
        listv.setOnItemClickListener(this);
    }

    @OnClick(R.id.public_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ManagerAdapter managerAdapter = null;
        if (adapterView.getAdapter() instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter ha = (HeaderViewListAdapter) adapterView.getAdapter();
            managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
        } else if (adapterView.getAdapter() instanceof ManagerAdapter) {
            managerAdapter = (ManagerAdapter) adapterView.getAdapter();
        }
        Announcement announcement = (Announcement) managerAdapter.getItem(i);
        Intent intent = new Intent();
        intent.setClass(context, NoticedetailsActivity.class);
        intent.putExtra("nid", announcement.getId());
        ActivityUtils.startActivity(intent);

    }
}
