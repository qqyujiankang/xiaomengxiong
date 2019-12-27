package com.example.et.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.et.R;
import com.example.et.entnty.Assetswallet;
import com.example.et.util.StringUtils;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

public class NewAssAdaper1 extends ManagerAdapter {
    public NewAssAdaper1(Context context, List list, Lifeful lifefu) {
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
        @BindView(R.id.Rl)
        RelativeLayout Rl;

        @Override
        protected int getItemLayoutResId() {
            return R.layout.new_adaper_ass;
        }

        @Override
        public void setViewData(int position, List list) {
            Assetswallet assetswallet = (Assetswallet) list.get(position);
            if (assetswallet.getName().equals("USDT")) {
//                if (assetswallet.getName().equals("USDT")) {
//
//
//                } else if (assetswallet.getName().equals("DAAS")) {
//                    glideimv(context.getResources().getDrawable(R.mipmap.daas));
//                } else if (assetswallet.getName().equals("HOPE")) {
//                    glideimv(context.getResources().getDrawable(R.mipmap.hope));
//                }
                Rl.setVisibility(View.VISIBLE);
                glideimv(context.getResources().getDrawable(R.mipmap.usdt));
                tvName.setText(assetswallet.getName());
                tvNumber.setText(StringUtils.calculateProfit(Double.valueOf(assetswallet.getNumber()), 5));
            } else {
                Rl.setVisibility(View.GONE);
            }


        }

        private void glideimv(Drawable drawable) {
            Glide.with(context)
                    .load(drawable)
                    .into(ivView);
        }


    }
}
