package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ToastUtils;
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
 * 意见反馈
 */
public class FeedbackActivity extends BaseActivity {


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
    @BindView(R.id.edit_feedback)
    EditText editFeedback;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.public_button)
    Button publicButton;
    private Context context;
    private static final int MAX_COUNT = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        publicButton.setText(getString(R.string.submit_feedback));
        publicTitleTv.setText(getString(R.string.feedback));
        editFeedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (MAX_COUNT - editable.length() >= 0) {
                    tvCancel.setText(getString(R.string.words_remaining) + (MAX_COUNT - editable.length()));
                }

            }
        });
    }

    @OnClick({R.id.public_back, R.id.public_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.public_button:
                if (examine()) {
                    redata();
                }

                break;
            default:
        }
    }

    private String text;

    private boolean examine() {
        text = editFeedback.getText().toString().trim();
        if (StringUtils.isEmpty(text)) {
            ToastUtils.showShort(R.string.Content_of_the_feedback);
            return false;
        }
        return true;
    }

    private void redata() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("text", text);


            TaskPresenterUntils.lifeful(Constant.feedback, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("======钱包======" + success);


                    Map<String, Object> stringMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();
                    if (stringMap.get(KeyValueConstants.CODE).equals("200")) {
                        finish();
                    }
                    ToastUtils.showShort(stringMap.get(KeyValueConstants.MSG).toString());

                }


            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
