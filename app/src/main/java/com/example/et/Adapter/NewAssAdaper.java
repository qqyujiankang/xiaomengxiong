package com.example.et.Adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

public class NewAssAdaper extends ManagerAdapter {
    public NewAssAdaper(Context context, List list, Lifeful lifefu) {
        super(context, list, lifefu);
    }

    @Override
    protected BaseHolder getHolder(Context context) {
        return new ViewHolder();
    }

    class ViewHolder extends BaseHolder {

        @BindView(R.id.iv_view)
        ImageView ivView;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.iv_bank)
        ImageView ivBank;
        @BindView(R.id.RL_Usdt)
        RelativeLayout RLUsdt;

        @Override
        protected int getItemLayoutResId() {
            return R.layout.new_adaper_ass;
        }

        @Override
        public void setViewData(int position, List list) {

        }
    }
}
