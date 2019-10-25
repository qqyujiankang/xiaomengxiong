package com.example.et.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.et.R;
import com.example.et.util.lifeful.Lifeful;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.tv_Friends_share)
    TextView tvFriendsShare;
    @BindView(R.id.tv_my_team)
    TextView tvMyTeam;
    @BindView(R.id.tv_Node_returns)
    TextView tvNodeReturns;
    @BindView(R.id.tv_gain_recording)
    TextView tvGainRecording;
    @BindView(R.id.tv_address_binding)
    TextView tvAddressBinding;
    @BindView(R.id.tv_apassword)
    TextView tvApassword;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.tv_version_information)
    TextView tvVersionInformation;
    @BindView(R.id.public_button)
    Button publicButton;
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
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        super.initView();

    }
}
