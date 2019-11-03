package com.example.et.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.et.Adapter.MyFragmentPagerAdapter;
import com.example.et.R;
import com.example.et.View.CustomScrollViewPager;
import com.example.et.fragment.ContractFragment;
import com.example.et.fragment.HeadFragment;
import com.example.et.fragment.MyFragment;
import com.example.et.fragment.WalletFragment;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.meiqiasdk.util.MQConfig;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(0);

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
}
