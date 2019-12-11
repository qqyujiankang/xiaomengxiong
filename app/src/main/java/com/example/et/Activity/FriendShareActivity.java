package com.example.et.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.et.R;
import com.example.et.Ustlis.ImageLoaderUtil;
import com.example.et.Ustlis.ScreenUtils;
import com.example.et.Ustlis.StatusBarUtil;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.Ustlis.ZXingUtils;
import com.example.et.util.CacheUtils;
import com.example.et.util.ImageUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.constant.CacheConstants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.et.Ustlis.StatusBarUtil.setRootViewFitsSystemWindows;
import static com.example.et.Ustlis.ZXingUtils.createQRCodeBitmap;
import static com.example.et.Ustlis.phoneustl.isphoneisnobiel;
import static com.example.et.Ustlis.phoneustl.settingemail;
import static com.example.et.Ustlis.phoneustl.settingphone;

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
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.Rl_mldz_chufanginfo_weblayout)
    RelativeLayout RlMldzChufanginfoWeblayout;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_2)
    TextView tv2;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = FriendShareActivity.this;
        setContentView(R.layout.activity_friend_share);
        setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(R.string.Friends_share);
        rlBacground.setBackgroundResource(R.color.p_transparency);
        //  ImageLoaderUtil.setGlideim1(FriendShareActivity.this, ll, R.mipmap.uploads);
        Glide.with(context).load(R.mipmap.uploads).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                int w = resource.getMinimumWidth();
                int h = resource.getMinimumHeight();
                LogUtils.w("手机----------w" + ScreenUtils.getScreenWidth(), "33-----------w---" + w + "---------h--------" + h);
                ll.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(), (ScreenUtils.getScreenWidth() * h) / w));//720*365
                ll.setBackground(resource);

            }

        });
        ivCode.setImageBitmap(createQRCodeBitmap("https://api.etac.io/login/index.html?f_code=" + CacheUtils.getInstance().getString(CacheConstants.i_code), 180, 180, "UTF-8", "H", "1", Color.BLACK, Color.WHITE));
        tvCode.setText(CacheUtils.getInstance().getString(CacheConstants.i_code));
        if (isphoneisnobiel(CacheUtils.getInstance().getString(CacheConstants.PHONE)).equals("1")) {
            tv2.setText(settingphone(CacheUtils.getInstance().getString(CacheConstants.PHONE)));
        } else if (isphoneisnobiel(CacheUtils.getInstance().getString(CacheConstants.PHONE)).equals("2")) {
            tv2.setText(settingemail(CacheUtils.getInstance().getString(CacheConstants.PHONE)));
        }
        ll.setOnLongClickListener(new View.OnLongClickListener() {
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
