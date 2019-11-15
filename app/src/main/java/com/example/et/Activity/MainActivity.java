package com.example.et.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.et.Adapter.MyFragmentPagerAdapter;
import com.example.et.R;
import com.example.et.Ustlis.ListDatasUtils;
import com.example.et.Ustlis.StatusBarUtil;
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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.et.Ustlis.StatusBarUtil.setRootViewFitsSystemWindows;

/**
 * 主框架
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


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


    private Context context;
    private int curIndex = 0;
    private ArrayList<Fragment> fragments;
    HeadFragment headFragment=new HeadFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        setContentView(R.layout.activity_main);
        setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            curIndex = savedInstanceState.getInt("curIndex");
        }
        initView();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curIndex", curIndex);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);


        // init view pager
        fragments = ListDatasUtils.getListFragmentMainActivity();
        FragmentUtils.add(getSupportFragmentManager(), fragments, R.id.fragmentLayout, curIndex);
        mTabRadioGroup.setOnCheckedChangeListener(this);
//        headFragment.setOnButtonClick(new HeadFragment.OnButtonClick() {
//            @Override
//            public void onClick(View view) {
//                showCurrentFragment(2);
//                setBottomView(2);
//            }
//        });
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


    private int fragment_flag;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        fragment_flag = intent.getIntExtra("fragment_flag", 0);
        if (fragment_flag == 2) { // 我的
            // mViewPager.setCurrentItem(2);
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
            FragmentUtils.showHide(curIndex = index, fragments);
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

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.today_tab:
                showCurrentFragment(0);
                setBottomView(0);
                break;
            case R.id.record_tab:
                showCurrentFragment(1);
                setBottomView(1);
                break;
            case R.id.contact_tab:
                showCurrentFragment(2);
                setBottomView(2);
                break;
            case R.id.settings_tab:
                showCurrentFragment(3);
                setBottomView(3);
                break;
            default:
        }
    }

    private void setBottomView(int i) {
        switch (i) {
            case 0:
                todayTab.setChecked(true);
                contactTab.setChecked(false);
                settingsTab.setChecked(false);
                recordTab.setChecked(false);
                break;
            case 1:
                todayTab.setChecked(false);
                contactTab.setChecked(false);
                settingsTab.setChecked(false);
                recordTab.setChecked(true);
                break;
            case 2:
                todayTab.setChecked(false);
                contactTab.setChecked(true);
                settingsTab.setChecked(false);
                recordTab.setChecked(false);
                break;
            case 3:
                todayTab.setChecked(false);
                contactTab.setChecked(false);
                settingsTab.setChecked(true);
                recordTab.setChecked(false);
                break;
            default:
        }

    }
}

