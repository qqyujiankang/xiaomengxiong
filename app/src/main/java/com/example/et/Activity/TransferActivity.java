package com.example.et.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ScreenUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.View.PayDialog;
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

/**
 * 转账
 */
public class TransferActivity extends BaseActivity {

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
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.et_account_number)
    EditText etAccountNumber;

    @BindView(R.id.tv_currency)
    TextView tvCurrency;
    @BindView(R.id.et_transfer_amount)
    EditText etTransferAmount;
    private Context context;
    private int id;
    private String pay_pass, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = TransferActivity.this;
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);
        getIntentDatas();
        initView();

    }

    @Override
    public void getIntentDatas() {
        super.getIntentDatas();
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");

    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.transfer));
        publicButton.setText(getString(R.string.confirm));
        tvCurrency.setText(name);
    }

    @OnClick({R.id.public_back, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.public_button:
                if (CheckTheValidation()) {
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
                }
                break;
            default:
        }
    }

    String AccountNumber, TransferAmount, Currency;

    private boolean CheckTheValidation() {
        AccountNumber = etAccountNumber.getText().toString().trim();
        TransferAmount = etTransferAmount.getText().toString().trim();
        if (StringUtils.isEmpty(AccountNumber)) {
            ToastUtils.showShort(R.string.please_fill_in_the_account_number);
            return false;
        }
        if (StringUtils.isEmpty(TransferAmount)) {
            ToastUtils.showShort(R.string.Please_fill_in_the_transfer_amount);
            return false;
        }
//

        return true;
    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("glodtype", id);
            jsonObject.put("money", TransferAmount);
            jsonObject.put("pay_pass", pay_pass);
            jsonObject.put("touser", AccountNumber);


            TaskPresenterUntils.lifeful(Constant.userupdate, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======钱包======" + success);

                    Map<String, Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount(TransferActivity.this, success, null, false).getStringMap();
                    if (objectPagebean.get(KeyValueConstants.CODE).equals("200")) {
                        LogUtils.i("======钱包======" + objectPagebean.get(KeyValueConstants.MSG));
                        finish();
                    }
                    ToastUtils.showShort(objectPagebean.get(KeyValueConstants.MSG).toString());

                }


            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
