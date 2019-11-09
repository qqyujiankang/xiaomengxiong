package com.example.et.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.et.Activity.AddressbindingActivity;
import com.example.et.Activity.FeedbackActivity;
import com.example.et.Activity.FriendShareActivity;
import com.example.et.Activity.GainRecordingActivity;
import com.example.et.Activity.IanguageSwitchActivity;
import com.example.et.Activity.LoginActivity;
import com.example.et.Activity.MyTeamActivity;
import com.example.et.Activity.NodeReturnsActivity;
import com.example.et.Activity.PersonalDataActivity;
import com.example.et.Activity.SecurityCenterconActivity;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.ImageLoaderUtil;
import com.example.et.Ustlis.StatusBarUtils;
import com.example.et.util.AppUtils;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.lifeful.Lifeful;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 */
public class MyFragment extends BaseFragment {


    @BindView(R.id.iv_holder)
    ImageView ivHolder;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.Rl_Record)
    RelativeLayout RlRecord;
    @BindView(R.id.tv_Friends_share)
    TextView tvFriendsShare;
    @BindView(R.id.Ll_Friends_share)
    LinearLayout LlFriendsShare;
    @BindView(R.id.tv_my_team)
    TextView tvMyTeam;
    @BindView(R.id.ll_my_team)
    LinearLayout llMyTeam;
    @BindView(R.id.tv_Node_returns)
    TextView tvNodeReturns;
    @BindView(R.id.ll_returns)
    LinearLayout llReturns;
    @BindView(R.id.tv_gain_recording)
    TextView tvGainRecording;
    @BindView(R.id.ll_gain_recording)
    LinearLayout llGainRecording;
    @BindView(R.id.tv_address_binding)
    TextView tvAddressBinding;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.tv_binding)
    TextView tvBinding;
    @BindView(R.id.Ll_USDT_address_binding)
    LinearLayout LlUSDTAddressBinding;
    @BindView(R.id.tv_apassword)
    TextView tvApassword;
    @BindView(R.id.ll_set_password)
    LinearLayout llSetPassword;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.ll_feedback)
    LinearLayout llFeedback;
    @BindView(R.id.tv_version_information)
    TextView tvVersionInformation;
    @BindView(R.id.ll_version_information)
    LinearLayout llVersionInformation;
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.ll_language)
    LinearLayout llLanguage;
    @BindView(R.id.tvtv_version_information)
    TextView tvtvVersionInformation;
    @BindView(R.id.iv_bank_01)
    ImageView ivBank01;
    @BindView(R.id.iv_jiedian)
    ImageView ivJiedian;
    private Context context;
    Unbinder unbinder;

    private Lifeful lifeful;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        lifeful = (Lifeful) getActivity();
    }


    @Override
    protected int setContentView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void lazyLoad() {

        LogUtils.i("==============wode===");
        StatusBarUtils.with(getActivity())
                .setColor(getResources().getColor(R.color.orange))
                .init();

    }

    @Override
    public void initView() {
        super.initView();
        if (!CacheUtils.getInstance().getString(CacheConstants.usdtaddress).equals("null")) {
            // tvBinding.setVisibility(View.GONE);
            tvBinding.setText(getString(R.string.Is_binding));
        }
        publicButton.setText(getString(R.string.quit));
        //头像
        ImageLoaderUtil.loadCircleImage(context, CacheUtils.getInstance().getString(CacheConstants.photo_url)
                + CacheUtils.getInstance().getString(CacheConstants.photo), ivHolder);
        tvName.setText(CacheUtils.getInstance().getString(CacheConstants.name));
        tvPhone.setText(CacheUtils.getInstance().getString(CacheConstants.PHONE));
        tvtvVersionInformation.setText(AppUtils.getAppVersionName());
        if (CacheUtils.getInstance().getString(CacheConstants.jiedian).equals("1")) {
            ivJiedian.setVisibility(View.GONE);
            llReturns.setVisibility(View.GONE);
        } else if (CacheUtils.getInstance().getString(CacheConstants.jiedian).equals("2")) {
            ivJiedian.setImageDrawable(getResources().getDrawable(R.mipmap.node));
        } else if (CacheUtils.getInstance().getString(CacheConstants.jiedian).equals("3")) {
            ivJiedian.setImageDrawable(getResources().getDrawable(R.mipmap.chaojijiedian));
        }

    }

    @OnClick({R.id.Rl_Record,
            R.id.Ll_Friends_share, R.id.ll_my_team, R.id.ll_returns, R.id.ll_gain_recording, R.id.Ll_USDT_address_binding,
            R.id.ll_set_password, R.id.ll_feedback, R.id.ll_version_information, R.id.public_button, R.id.ll_language})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Rl_Record:
                ActivityUtils.startActivity(PersonalDataActivity.class);
                break;
            case R.id.Ll_Friends_share:
                ActivityUtils.startActivity(FriendShareActivity.class);
                break;
            case R.id.ll_my_team:
                ActivityUtils.startActivity(MyTeamActivity.class);
                break;
            case R.id.ll_returns:
                ActivityUtils.startActivity(NodeReturnsActivity.class);
                break;

            case R.id.ll_gain_recording:
                // TODO: 2019/10/25
                ActivityUtils.startActivity(GainRecordingActivity.class);
                break;
            case R.id.Ll_USDT_address_binding:
                if (CacheUtils.getInstance().getString(CacheConstants.usdtaddress).equals("null")) {
                    ActivityUtils.startActivity(AddressbindingActivity.class);
                }

                break;
            case R.id.ll_set_password:
                ActivityUtils.startActivity(SecurityCenterconActivity.class);
                break;
            case R.id.ll_feedback:
                ActivityUtils.startActivity(FeedbackActivity.class);
                break;
            case R.id.ll_version_information:
                break;
            case R.id.public_button:
                CacheUtils.getInstance().put(CacheConstants.TOKEN, "");
                ActivityUtils.finishActivity(context);
                ActivityUtils.startActivity(LoginActivity.class);

                break;
            case R.id.ll_language:
                ActivityUtils.startActivity(IanguageSwitchActivity.class);
                break;
            default:
        }
    }
}
