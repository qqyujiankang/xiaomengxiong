package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.CurrencyaddressAdapter;
import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.View.AlipayPopuWindow;
import com.example.et.entnty.Ass;
import com.example.et.entnty.Currencyaddress;
import com.example.et.util.CacheUtils;
import com.example.et.util.JsonUtil;
import com.example.et.util.LogUtils;
import com.example.et.util.StringUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.constant.KeyValueConstants;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Hope
 */
public class HopePropertyActivityActivity extends BaseActivity {

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
    @BindView(R.id.Rl_banl)
    TextView RlBanl;
    @BindView(R.id.Rl_ban2)
    RelativeLayout RlBan2;
    @BindView(R.id.RL_Hope)
    RelativeLayout RLHope;
    @BindView(R.id.btn_top_up)
    Button btnTopUp;
    @BindView(R.id.btn_Mention_money)
    Button btnMentionMoney;
    @BindView(R.id.btn_transfer)
    Button btnTransfer;
    @BindView(R.id.btn_record)
    Button btnRecord;
    @BindView(R.id.tv_rental)
    TextView tvRental;
    @BindView(R.id.tv_usable)
    TextView tvUsable;
    @BindView(R.id.tv_convert)
    TextView tvConvert;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.ll)
    LinearLayout ll;
    private Context context;
    Ass ass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_hope_property_activity);
        ButterKnife.bind(this);
        getIntentDatas();
        initView();
        requestDatas();

    }


    @Override
    public void getIntentDatas() {
        super.getIntentDatas();
        ass = getIntent().getParcelableExtra("ass");

        if (ass.getId() == 8) {
            btnTopUp.setVisibility(View.GONE);
        }
        RlBanl.setText(ass.getName());
        tvRental.setText(StringUtils.calculateProfit(Double.valueOf(ass.getNumber())));
        tvUsable.setText(StringUtils.calculateProfit(Double.valueOf(ass.getNewnumber())));
        tvConvert.setText(StringUtils.calculateProfit(Double.valueOf(ass.getCny())));

    }


    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(ass.getName() + getString(R.string.HOPE_the_asset));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                ManagerAdapter managerAdapter = null;
                if (parent.getAdapter() instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
                    managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
                } else if (parent.getAdapter() instanceof ManagerAdapter) {
                    managerAdapter = (ManagerAdapter) parent.getAdapter();
                }
                Currencyaddress currencyaddress = (Currencyaddress) managerAdapter.getItem(i);
                Intent intent = new Intent();
                intent.setClass(context, AddressbindingActivity.class);
                intent.putExtra("id", currencyaddress.getId());
                intent.putExtra("name", currencyaddress.getString());
                ActivityUtils.startActivity(intent);

            }
        });
    }

    AlipayPopuWindow alipayPopuWindow;

    @OnClick({R.id.public_back, R.id.public_other, R.id.btn_top_up, R.id.btn_Mention_money, R.id.btn_transfer, R.id.btn_record})
    public void onViewClicked(View view) {
        Intent intent = new Intent();

        intent.putExtra("id", ass.getId());
        switch (view.getId()) {

            case R.id.public_back:
                finish();
                break;
            case R.id.public_other:
                ActivityUtils.startActivity(AddressbindingActivity.class);
                break;
            case R.id.btn_top_up:
                intent.setClass(context, TopupActivity.class);
                ActivityUtils.startActivity(intent);//RecordActivity
                break;
            case R.id.btn_Mention_money:
                alipayPopuWindow = new AlipayPopuWindow((Activity) context, onItemClickListener, null, currencyaddresses);
                alipayPopuWindow.showAtLocation(ll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


                break;
            case R.id.btn_record:
                intent.setClass(context, RecordActivity.class);
                ActivityUtils.startActivity(intent);//
                break;
            case R.id.btn_transfer:
                intent.setClass(context, TransferActivity.class);
                intent.putExtra("name", ass.getName());
                ActivityUtils.startActivity(intent);//
                break;


            default:
        }
    }

    private List<Currencyaddress> currencyaddresses = new ArrayList<>();
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
            ManagerAdapter managerAdapter = null;
            if (parent.getAdapter() instanceof HeaderViewListAdapter) {
                HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
                managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
            } else if (parent.getAdapter() instanceof ManagerAdapter) {
                managerAdapter = (ManagerAdapter) parent.getAdapter();
            }
            Currencyaddress currencyaddress = (Currencyaddress) managerAdapter.getItem(position);
            Intent intent = new Intent();
            intent.setClass(context, MentionmoneyActivity.class);
            intent.putExtra("id", currencyaddress.getId());
            intent.putExtra("Number", StringUtils.calculateProfit(Double.valueOf(ass.getNumber())));
            intent.putExtra("stringname", currencyaddress.getStringname());
            intent.putExtra("name", currencyaddress.getString());
            ActivityUtils.startActivity(intent);
            alipayPopuWindow.dismiss();

//            Contract contract = (Contract) managerAdapter.getItem(position);
//            id = contract.getId();
//            tvUSDT.setText(contract.getNumber() + "USTD");

        }
    };

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("type", ass.getId());

            TaskPresenterUntils.lifeful(Constant.goldaddress, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    Map<String, Object> map = JsonUtil.parseJSON(success);
                    LogUtils.i("===============" + success + "========");
                    if (ass.getName().equals("USDT")) {

                        try {
                            JSONArray myJsonObject = new JSONArray(map.get(KeyValueConstants.DATA).toString());
                            for (int i = 0; i < myJsonObject.length(); i++) {
                                currencyaddresses.add(new Currencyaddress(myJsonObject.getString(i), ass.getName(), ass.getId()));
                            }


                        } catch (JSONException e) {

                        }
                    } else if (ass.getName().equals("HOPE")) {
                        Map<String, Object> stringObjectMap = JsonUtil.parseJSON(map.get(KeyValueConstants.DATA).toString());
                        for (int i = 0; i < stringObjectMap.size(); i++) {
                            if (stringObjectMap.get(i) != null) {
                                currencyaddresses.add(new Currencyaddress(stringObjectMap.get(i).toString(), ass.getName(), ass.getId()));
                            }

                        }
                        LogUtils.i("========" + stringObjectMap.get("1"));
                    }
                    lv.setAdapter(new CurrencyaddressAdapter(context, currencyaddresses, null));

                }

            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
