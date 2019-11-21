package com.example.et.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.Ustlis.ImageLoaderUtil;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.util.CacheUtils;
import com.example.et.util.ImageUtils;
import com.example.et.util.constant.CacheConstants;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分享好友
 */
public class FriendShareActivity extends BaseActivity {


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
    @BindView(R.id.mldz_chufanginfo_weblayout)
    ImageView mldzChufanginfoWeblayout;
    @BindView(R.id.ll)
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_share);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(R.string.Friends_share);

        // .go("");
        ImageLoaderUtil.setGlideim(FriendShareActivity.this, mldzChufanginfoWeblayout, CacheUtils.getInstance().getString(CacheConstants.i_code_img));
        mldzChufanginfoWeblayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ImageUtils.saveBitmap(FriendShareActivity.this, ImageUtils.view2Bitmap(view));
                ToastUtils.showLong("图片保存成功");
                return true;
            }
        });
    }

    @OnClick(R.id.public_back)
    public void onViewClicked() {
        finish();
    }
}
