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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ScreenUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.View.AlipayPopuWindow;
import com.example.et.View.AnswerDiog;
import com.example.et.View.MyDiog;
import com.example.et.View.SwipeCaptchaViewDialog;
import com.example.et.entnty.Answer;
import com.example.et.entnty.Contract;
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
 * 预约合约
 */
public class AppointmentcontractActivity extends BaseActivity {

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
    @BindView(R.id.ll_contract_amount)
    LinearLayout llContractAmount;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tvUSDT)
    TextView tvUSDT;
    private Context context;
    private Lifeful lifeful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        lifeful = this;
        setContentView(R.layout.activity_appointmentcontract);
        ButterKnife.bind(this);

           initView();
        requestDatas();

    }


    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.Appointment_contract));
        publicButton.setText(getString(R.string.confirm));
        MyDiog myDiog = new MyDiog(context, CacheUtils.getInstance().getString(CacheConstants.r_gold), 1);
        myDiog.show();
        myDiog.getWindow().setLayout(ScreenUtils.getScreenWidth() - 200, LinearLayout.LayoutParams.WRAP_CONTENT);


    }

    AlipayPopuWindow alipayPopuWindow;

    SwipeCaptchaViewDialog dialog;

    @OnClick({R.id.public_back, R.id.ll_contract_amount, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.ll_contract_amount:
                alipayPopuWindow = new AlipayPopuWindow((Activity) context, onItemClickListener, lifeful, null);
                alipayPopuWindow.showAtLocation(ll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                break;
            case R.id.public_button:
                if (!StringUtils.isEmpty(tvUSDT.getText().toString().trim())) {
//                    AnswerDiog answerDiog=new AnswerDiog(this,onClickListener, onClickListener1, problem, answers);
//////                    answerDiog.show();
//////                    answerDiog.getWindow().setLayout(ScreenUtils.getScreenWidth()-100 , LinearLayout.LayoutParams.WRAP_CONTENT);


                    dialog = new SwipeCaptchaViewDialog(context, id, lifeful);
                    dialog.show();
                    dialog.getWindow().setLayout(ScreenUtils.getScreenWidth() - 100, LinearLayout.LayoutParams.WRAP_CONTENT);
                } else {
                    ToastUtils.showShort(R.string.Please_select_contract_amount);
                }
                break;
            default:

        }
    }

    private List<Answer> answers = new ArrayList<>();
    private String problem, answerok, problemid;
    private AdapterView.OnItemClickListener onClickListener1 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
            ManagerAdapter managerAdapter = null;
            if (parent.getAdapter() instanceof HeaderViewListAdapter) {
                HeaderViewListAdapter ha = (HeaderViewListAdapter) parent.getAdapter();
                managerAdapter = (ManagerAdapter) ha.getWrappedAdapter();
            } else if (parent.getAdapter() instanceof ManagerAdapter) {
                managerAdapter = (ManagerAdapter) parent.getAdapter();
            }

            Answer contract = (Answer) managerAdapter.getItem(position);
            for (Answer bean : answers) {//全部设为未选中
                bean.setChecked(false);
            }
            answerok = contract.getString();
            contract.setChecked(true);//点击的设为选中
            managerAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void requestDatas() {
        super.requestDatas();
        // redata(0);

    }

    String url;

    private void redata(int i) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            if (i == 1) {
                jsonObject.put("cid", id);
                jsonObject.put("problemid", problemid);
                jsonObject.put("answerok", answerok);
                url = Constant.tocontract;
            } else {
                url = Constant.problem;
            }


            TaskPresenterUntils.lifeful(url, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("=======lifeful====" + success);
                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();
                    if (resultMap.get(KeyValueConstants.CODE).equals("200")) {
                        if (i == 0) {
                            problem = resultMap.get("problem").toString();
                            try {
                                String s = resultMap.get("answer").toString();
                                problemid = resultMap.get("id").toString();
                                JSONArray myJsonObject = new JSONArray(s);
                                for (int i = 0; i < myJsonObject.length(); i++) {
                                    answers.add(new Answer(myJsonObject.getString(i)));
                                }
                            } catch (JSONException e) {

                            }
                        } else {
                            Intent i = new Intent();
                            i.setClass(AppointmentcontractActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.putExtra("fragment_flag", 2);
                            startActivity(i);
                            finish();

                        }

                    }
                    if (!resultMap.get(KeyValueConstants.MSG).equals("")) {
                        ToastUtils.showShort(resultMap.get(KeyValueConstants.MSG).toString());
                    }


                }

            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_confirm:
                    LogUtils.i("====================" + answerok);
                    redata(1);

                    break;
                default:
            }

        }
    };

    private int id;
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

            Contract contract = (Contract) managerAdapter.getItem(position);
            id = contract.getId();
            tvUSDT.setText(contract.getNumber() + getString(R.string.USDT));
            alipayPopuWindow.dismiss();
        }
    };

}
