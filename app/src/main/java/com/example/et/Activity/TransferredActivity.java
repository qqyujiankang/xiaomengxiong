package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ScreenUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.View.AlipayPopuWindow;
import com.example.et.View.PayDialog;
import com.example.et.entnty.Currencyaddress;
import com.example.et.entnty.Transferred;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.StringUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.constant.KeyValueConstants;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferredActivity extends BaseActivity {

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
    @BindView(R.id.tv_01)
    TextView tv01;
    @BindView(R.id.tv_02)
    TextView tv02;
    @BindView(R.id.tv_03)
    EditText tv03;
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.Rl_1)
    RelativeLayout Rl1;
    @BindView(R.id.ll)
    LinearLayout ll;
    private String currency, toassets, pay_pass, name;
    AlipayPopuWindow alipayPopuWindow;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_transferred);
        ButterKnife.bind(this);
        getIntentDatas();
        initView();
    }

    @Override
    public void getIntentDatas() {
        super.getIntentDatas();
        currency = getIntent().getStringExtra("currency");
        name = getIntent().getStringExtra("name");
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.Transferred));
        publicButton.setText(getString(R.string.Transferred));
        tv01.setText(currency);
    }

    int anInt;

    @OnClick({R.id.public_back, R.id.Rl_1, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.Rl_1:
                if (name.equals(getString(R.string.things_personal))) {
                    anInt = 1;
                } else if (name.equals(getString(R.string.Reference_Asset))) {
                    anInt = 2;
                }
                alipayPopuWindow = new AlipayPopuWindow((Activity) context, onItemClickListener, null, null, anInt,currency);
                alipayPopuWindow.showAtLocation(ll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                break;
            case R.id.public_button:
                //  requestDatas();
                if (!StringUtils.isEmpty(tv03.getText().toString())) {
                    if (Double.parseDouble(tv03.getText().toString()) >= 100) {

                        PayDialog payDialog = new PayDialog(context) {
                            @Override
                            public void clickCallBack(String str) {
                                pay_pass = str;
                                if (!StringUtils.isEmpty(pay_pass)) {
                                    requestDatas();
                                } else {
                                    ToastUtils.showShort(R.string.phoenumber_payment_code_ok);
                                }

                            }

                            @Override
                            public void clickBack() {

                            }
                        };
                        payDialog.getWindow().setLayout(ScreenUtils.getScreenWidth() - 200, LinearLayout.LayoutParams.WRAP_CONTENT);
                        payDialog.setCancelable(false);
                        payDialog.setView(new EditText(context));
                        payDialog.show();

                    } else {
                        ToastUtils.showShort(getString(R.string.tets1));
                    }
                }
                break;
        }
    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("paypass", pay_pass);
            jsonObject.put("moneytype", currency);
            jsonObject.put("number", tv03.getText().toString().trim());
            if (name.equals(getString(R.string.things_personal))) {
                jsonObject.put("formassets", "all");
            } else {
                jsonObject.put("formassets", "contract");
            }

            jsonObject.put("toassets", toassets);

            LogUtils.i("划转========" + jsonObject);
            TaskPresenterUntils.lifeful(Constant.assetstransfer, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, true).getStringMap();

                    if (resultMap.get(KeyValueConstants.CODE).equals("200")) {
                        PersonalAssetsActivity.aBoolean = true;
                        finish();
                    }
                    ToastUtils.showShort(resultMap.get(KeyValueConstants.MSG).toString());


                }
            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
            Transferred transferred = (Transferred) managerAdapter.getItem(position);
            toassets = transferred.getTransferred();
            tv02.setText(transferred.getName());
            alipayPopuWindow.dismiss();


        }
    };
}
