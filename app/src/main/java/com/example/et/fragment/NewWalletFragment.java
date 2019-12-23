package com.example.et.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.et.Activity.PersonalAssetsActivity;
import com.example.et.Activity.PersonalDataActivity;
import com.example.et.Adapter.AssAdaper;
import com.example.et.Adapter.NewAssAdaper;
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

public class NewWalletFragment extends BaseFragment{
    @BindView(R.id.listv)
    ListView listv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
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

    @Override
    public void initView() {
        super.initView();
        requestDatas();
        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ActivityUtils.startActivity(PersonalAssetsActivity.class);
            }
        });
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
                            listv.setAdapter(new NewAssAdaper(context, asses, null));
                        }
                    }


                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
