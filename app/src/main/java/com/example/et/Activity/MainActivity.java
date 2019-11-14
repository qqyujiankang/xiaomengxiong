package com.example.et.Activity;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.et.Adapter.MyFragmentPagerAdapter;
import com.example.et.R;
import com.example.et.Ustlis.ListDatasUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.View.CustomScrollViewPager;
import com.example.et.entnty.VersionInfo;
import com.example.et.fragment.ContractFragment;
import com.example.et.fragment.HeadFragment;
import com.example.et.fragment.MyFragment;
import com.example.et.fragment.WalletFragment;
import com.example.et.util.AppUtils;
import com.example.et.util.FragmentUtils;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.meiqiasdk.util.MQConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主框架
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.fragment_vp)
    CustomScrollViewPager mViewPager;
    @BindView(R.id.today_tab)
    RadioButton todayTab;
    @BindView(R.id.record_tab)
    RadioButton recordTab;
    @BindView(R.id.contact_tab)
    RadioButton contactTab;
    @BindView(R.id.settings_tab)
    RadioButton settingsTab;
    @BindView(R.id.tabs_rg)
    RadioGroup mTabRadioGroup;
    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;
    private Context context;
    private int curIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            curIndex = savedInstanceState.getInt("curIndex");
        }
        initView();

    }


    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mFragments = new ArrayList<>();
        if (mFragments.size() == 0) {
            mFragments.add(new HeadFragment());
            mFragments.add(new WalletFragment());
            mFragments.add(new ContractFragment());
            mFragments.add(new MyFragment());
        }
        // init view pager
         mFragments = ListDatasUtils.getListFragmentMainActivity();
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
       //mViewPager.setOffscreenPageLimit(3);


        mTabRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
        MQConfig.init(this, "372713903d6abcc17515b00475bc2f04", new OnInitCallback() {
            @Override
            public void onSuccess(String clientId) {
                // Toast.makeText(MainActivity.this, "init success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int code, String message) {
                //   Toast.makeText(MainActivity.this, "int failure", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) mTabRadioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(mPageChangeListener);
    }

    private int fragment_flag;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        fragment_flag = intent.getIntExtra("fragment_flag", 0);
        if (fragment_flag == 2) { // 我的
            mViewPager.setCurrentItem(2);
            contactTab.setChecked(true);
            todayTab.setChecked(false);
            settingsTab.setChecked(false);
            recordTab.setChecked(false);

        }
    }

    public static void reStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void showCurrentFragment(int index) {
        if (index != curIndex) {
            FragmentUtils.showHide(curIndex = index, mFragments);
        }
    }

    /**
     * 更新app
     *
     * @param usVersionInfo
     */
    public void checkVersionSuccess(VersionInfo usVersionInfo) {

        if (!AppUtils.getAppVersionName().equals(usVersionInfo.getVersion())) {


        }
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, getString(R.string.zayt), Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                exitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}

