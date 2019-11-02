package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
 * 登录密码
 */
public class LogiPasswordActivity extends BaseActivity {

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
    @BindView(R.id.et_original_password)
    EditText etOriginalPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_ok_new_paswwosd)
    EditText etOkNewPaswwosd;
    private Context context;
    private String OriginalPassword;
    private String NewPassword;
    private String OkNewPaswwosd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = LogiPasswordActivity.this;
        setContentView(R.layout.activity_logi_password);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        super.initView();

        publicTitleTv.setText(getString(R.string.login_password));
        publicButton.setText(getString(R.string.confirm));
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

    /**
     * 检查信息是否全
     *
     * @return
     */
    private boolean examine() {
        OriginalPassword = etOriginalPassword.getText().toString();
        NewPassword = etNewPassword.getText().toString();
        OkNewPaswwosd = etOkNewPaswwosd.getText().toString();

        if (StringUtils.isEmpty(OriginalPassword)) {
            ToastUtils.showShort(R.string.Please_enter_the_original_password);
            return false;
        }
        if (StringUtils.isEmpty(NewPassword)) {
            ToastUtils.showShort(R.string.Please_enter_your_new_password);
            return false;
        }
        if (StringUtils.isEmpty(OkNewPaswwosd)) {
            ToastUtils.showShort(R.string.Please_enter_your_new_password_again);
            return false;
        }
        if (!NewPassword.equals(OkNewPaswwosd)) {
            ToastUtils.showShort(R.string.The_two_password_do_not_match);
            return false;
        }

        return true;
    }

    private void redata() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("pass", OriginalPassword);
            jsonObject.put("type", "1");
            jsonObject.put("newpass", NewPassword);
            jsonObject.put("newpasstwo", OkNewPaswwosd);


            TaskPresenterUntils.lifeful(Constant.uppass, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {



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
