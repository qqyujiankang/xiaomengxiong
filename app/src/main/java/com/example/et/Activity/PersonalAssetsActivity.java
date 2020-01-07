package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.et.Adapter.CurrencyaddressAdapter;
import com.example.et.Adapter.ListActivitynewAdapter;
import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.ListDatasUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.View.AlipayPopuWindow;
import com.example.et.View.MyGridView;
import com.example.et.entnty.Assetswallet;
import com.example.et.entnty.Currencyaddress;
import com.example.et.entnty.ListObject;
import com.example.et.util.CacheUtils;
import com.example.et.util.JsonUtil;
import com.example.et.util.LogUtils;
import com.example.et.util.StringUtils;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人资产
 */
public class PersonalAssetsActivity extends BaseActivity {

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
    @BindView(R.id.iv_view)
    ImageView ivView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.RL_Usdt)
    RelativeLayout RLUsdt;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.public_grid_view)
    MyGridView publicGridView;
    @BindView(R.id.ll)
    LinearLayout ll;
    private Context context;
    private String name = "HOPE";
    private Assetswallet assetswallet;
    private AdapterRealize adapterRealize;

    private Lifeful lifeful;
    AlipayPopuWindow alipayPopuWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = PersonalAssetsActivity.this;
        lifeful = PersonalAssetsActivity.this;
        setContentView(R.layout.activity_personal_assets);
        ButterKnife.bind(this);
        getIntentDatas();
        initView();
    }

    @Override
    public void getIntentDatas() {
        super.getIntentDatas();
        name = getIntent().getStringExtra("name");
        assetswallet = getIntent().getParcelableExtra("assetswallet");
    }

    public static boolean aBoolean;

    @Override
    protected void onResume() {
        super.onResume();
        if (aBoolean == true) {
            aBoolean = false;
            currencyaddresses = new ArrayList<>();
            requestDatas();

        }

    }

    private List<Currencyaddress> currencyaddresses = new ArrayList<>();

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            if (name.equals(getString(R.string.things_personal))) {
                jsonObject.put("type", "all");
            } else {
                jsonObject.put("type", "contract");
            }
            jsonObject.put("moneytype", assetswallet.getName());


            TaskPresenterUntils.lifeful(Constant.assetswalletone, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======钱包======all====" + success);

                    adapterRealize = new AdapterRealize();
                    Map<String, Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();
                    tvNumber.setText(StringUtils.calculateProfit(Double.valueOf(objectPagebean.get("number").toString()), 5));
                    if (objectPagebean.get(KeyValueConstants.CODE).equals("200")) {
                        if (objectPagebean.containsKey("address")) {
                            if (assetswallet.getName().equals("USDT")) {
                                if (!objectPagebean.get("address").toString().equals("null")) {
                                    currencyaddresses.add(new Currencyaddress(objectPagebean.get("address").toString(), assetswallet.getName(), 8));
                                    //List<Ass> asses = JsonUtil.stringToList(objectPagebean.get("address").toString(), Ass.class);
                                    if (currencyaddresses.size() != 0) {
                                        lv.setAdapter(new CurrencyaddressAdapter(context, currencyaddresses, null));
                                    } else {
                                        publicOther.setText(getString(R.string.Binding_address));
                                    }
                                } else {
                                    publicOther.setText(getString(R.string.Binding_address));
                                }
                            } else {
                                Map<String, Object> stringObjectMap = JsonUtil.parseJSON(objectPagebean.get("address").toString());

                                LogUtils.i("========" + stringObjectMap.get("1"));


                                currencyaddresses = testMapVoid(stringObjectMap, assetswallet);


                                lv.setAdapter(new CurrencyaddressAdapter(context, currencyaddresses, null));

                                publicOther.setText(getString(R.string.Binding_address));


                            }
                        }
                    }


                }


            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {
        super.initView();
        if (name.equals(getString(R.string.things_personal))) {
            lv.setVisibility(View.VISIBLE);
            publicGridView.setNumColumns(5);

        } else if (name.equals(getString(R.string.Reference_Asset))) {
            lv.setVisibility(View.GONE);
            publicGridView.setNumColumns(ListDatasUtils.getListActivity1(context, name, assetswallet.getName()).size());
            publicOther.setVisibility(View.GONE);
        }
        publicTitleTv.setText(name);
        if (assetswallet.getName().equals("USDT")) {
            glideimv(context.getResources().getDrawable(R.mipmap.usdt));

        } else if (assetswallet.getName().equals("DAAS")) {
            glideimv(context.getResources().getDrawable(R.mipmap.daas));
        } else if (assetswallet.getName().equals("HOPE")) {
            glideimv(context.getResources().getDrawable(R.mipmap.hope));
        }

//        Glide.with(context)
//                .load(assetswallet.getImg())
//                .into(ivView);
        tvName.setText(assetswallet.getName());
        tvNumber.setText(StringUtils.calculateProfit(Double.valueOf(assetswallet.getNumber()), 5));
        publicGridView.setAdapter(new ListActivitynewAdapter<>(context, ListDatasUtils.getListActivity1(context, name, assetswallet.getName()), null));
        publicGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                ManagerAdapter managerAdapter = null;
                if (parent.getAdapter() instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
                    managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
                } else if (parent.getAdapter() instanceof ManagerAdapter) {
                    managerAdapter = (ManagerAdapter) parent.getAdapter();
                }
                ListObject objectList = (ListObject) managerAdapter.getItem(i);
                Intent intent = new Intent();
                if (objectList.getName().equals(getString(R.string.top_up))) {

                    if (assetswallet.getName().equals("USDT")) {
                        intent.putExtra("id", 8);

                    } else if (assetswallet.getName().equals("DAAS")) {
                        intent.putExtra("id", 11);
                    } else if (assetswallet.getName().equals("HOPE")) {
                        intent.putExtra("id", 7);
                    }
                    intent.setClass(context, TopupActivity.class);
                    ActivityUtils.startActivity(intent);
                } else if (objectList.getName().equals(getString(R.string.Mention_money))) {
                    alipayPopuWindow = new AlipayPopuWindow((Activity) context, onItemClickListener, null, currencyaddresses, 0, null);
                    alipayPopuWindow.showAtLocation(ll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                } else if (objectList.getName().equals(getString(R.string.Transferred))) {
                    if (name.equals(getString(R.string.things_personal))) {
                        intent.putExtra("name", name);
                        intent.putExtra("currency", assetswallet.getName());
                        intent.setClass(context, TransferredActivity.class);
                        ActivityUtils.startActivity(intent);
                    } else {
//                        if (assetswallet.getName().equals("USDT")) {
//                            ToastUtils.showShort(getString(R.string.Temporarily_not_opened));
//                        } else {
                           intent.putExtra("name", name);
                        intent.putExtra("currency", assetswallet.getName());
                        intent.setClass(context, TransferredActivity.class);
                        ActivityUtils.startActivity(intent);
                        //   }

                    }
                } else if (objectList.getName().equals(getString(R.string.transfer))) {
                    if (assetswallet.getName().equals("USDT")) {
                        intent.putExtra("id", 8);

                    } else if (assetswallet.getName().equals("DAAS")) {
                        intent.putExtra("id", 11);
                    } else if (assetswallet.getName().equals("HOPE")) {
                        intent.putExtra("id", 7);
                    }
                    // ;//哪个钱包  个人 all 矿机 mining 合约  contract
                    if (name.equals(getString(R.string.things_personal))) {
                        intent.putExtra("assetstype", "all");
                    } else if (name.equals(getString(R.string.Reference_Asset))) {
                        intent.putExtra("assetstype", "contract");
                    }

                    intent.setClass(context, TransferActivity.class);
                    intent.putExtra("name", assetswallet.getName());
                    ActivityUtils.startActivity(intent);
                } else if (objectList.getName().equals(getString(R.string.record))) {
                    intent.putExtra("name", name);
                    intent.putExtra("moneytype", assetswallet.getName());
                    intent.setClass(context, RecordActivity.class);
                    ActivityUtils.startActivity(intent);
                }


            }
        });
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
                intent.putExtra("stringname", currencyaddress.getStringname());
                intent.putExtra("name", currencyaddress.getString());
                intent.putExtra("xiugan", "321");

                ActivityUtils.startActivity(intent);

            }
        });
        requestDatas();
    }

    private void glideimv(Drawable drawable) {
        Glide.with(context)
                .load(drawable)
                .into(ivView);
    }

    public static List<Currencyaddress> testMapVoid(Map<String, Object> map, Assetswallet ass) {
        List<Currencyaddress> currencyaddresses = new ArrayList<>();
        List listKey = new ArrayList();
        List listValue = new ArrayList();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            listKey.add(key);
            listValue.add(map.get(key));
        }


        for (int i = 0; i < listKey.size(); i++) {
            if (listValue.get(i) != null && !listValue.get(i).toString().equals("null")) {
                if (ass.getName().equals("DAAS")) {
                    currencyaddresses.add(new Currencyaddress(listValue.get(i).toString(), ass.getName(), 11));
                } else if (ass.getName().equals("HOPE")) {
                    currencyaddresses.add(new Currencyaddress(listValue.get(i).toString(), ass.getName(), 7));
                }

            }


        }
        return currencyaddresses;
    }

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
            intent.putExtra("Number", StringUtils.calculateProfit(Double.valueOf(assetswallet.getNumber()), 5));
            intent.putExtra("stringname", currencyaddress.getStringname());
            intent.putExtra("name", currencyaddress.getString());
            ActivityUtils.startActivity(intent);
            alipayPopuWindow.dismiss();

//            Contract contract = (Contract) managerAdapter.getItem(position);
//            id = contract.getId();
//            tvUSDT.setText(contract.getNumber() + "USTD");

        }
    };

    @OnClick({R.id.public_back, R.id.public_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.public_other:
                Intent intent = new Intent();
                intent.setClass(context, AddressbindingActivity.class);
                intent.putExtra("tepy", "bangding");
                if (assetswallet.getName().equals("USDT")) {
                    intent.putExtra("id", 8);

                } else if (assetswallet.getName().equals("HOPE")) {
                    intent.putExtra("id", 7);
                } else {
                    intent.putExtra("id", 11);
                }
                intent.putExtra("name", assetswallet.getName());
                ActivityUtils.startActivity(intent);
                break;
        }
    }
}
