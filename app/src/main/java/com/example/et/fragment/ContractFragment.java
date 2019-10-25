package com.example.et.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.et.R;
import com.example.et.util.BarUtils;
import com.example.et.util.lifeful.Lifeful;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 合约
 */

public class ContractFragment extends Fragment {

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
    @BindView(R.id.tv_not_available)
    TextView tvNotAvailable;
    @BindView(R.id.public_button)
    Button publicButton;
    @BindView(R.id.Rl_)
    RelativeLayout Rl;

    private Context context;
    Unbinder unbinder;

    private Lifeful lifeful;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity();
        lifeful = (Lifeful) getActivity();
        View view = inflater.inflate(R.layout.fragment_contract, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

        rlBacground.setBackgroundColor(context.getResources().getColor(R.color.orange));
        publicBack.setVisibility(View.GONE);
        publicTitleTv.setText(getString(R.string.menu_find));

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
