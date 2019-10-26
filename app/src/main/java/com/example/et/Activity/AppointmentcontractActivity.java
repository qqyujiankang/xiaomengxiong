package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
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

import com.example.et.Adapter.AnswerAdapter;
import com.example.et.Adapter.ManagerAdapter;
import com.example.et.Adapter.TestCheckOneAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.View.AlipayPopuWindow;
import com.example.et.View.AnswerDiog;
import com.example.et.entnty.Answer;
import com.example.et.entnty.Contract;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
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
    }

    AlipayPopuWindow alipayPopuWindow;


    @OnClick({R.id.public_back, R.id.ll_contract_amount, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.ll_contract_amount:
                alipayPopuWindow = new AlipayPopuWindow((Activity) context, onItemClickListener, lifeful);
                alipayPopuWindow.showAtLocation(ll, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                break;
            case R.id.public_button:
                //requestDatas();
//                answerPopuwindow = new AnswerPopuwindow((Activity) context, onClickListener, onClickListener1, lifeful);
//                answerPopuwindow.showAtLocation(ll, Gravity.CENTER_VERTICAL, 0, 0); //设置layout在PopupWindow中显示的位置
                new AnswerDiog(this, onClickListener, onClickListener1, problem, answers).show();

                break;
            default:

        }
    }

    private List<Answer> answers = new ArrayList<>();
    private String problem;

    @Override
    public void requestDatas() {
        super.requestDatas();

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            TaskPresenterUntils.lifeful(Constant.problem, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("=======lifeful====" + success);
                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();
                    if (resultMap.get(KeyValueConstants.CODE).equals("200")) {
                        problem = resultMap.get("problem").toString();
                        try {
                            String s = resultMap.get("answer").toString();
                            JSONArray myJsonObject = new JSONArray(s);
                            for (int i = 0; i < myJsonObject.length(); i++) {
                                answers.add(new Answer(myJsonObject.getString(i)));
                            }
                        } catch (JSONException e) {

                        }

                    } else {

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
            tvUSDT.setText(contract.getNumber() + "USTD");
            alipayPopuWindow.dismiss();
        }
    };
    private AdapterView.OnItemClickListener onClickListener1 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
 TestCheckOneAdapter.ViewHolder holder = (TestCheckOneAdapter.ViewHolder) view.getTag();
        holder.checkBox.toggle();
        }
    };
}
