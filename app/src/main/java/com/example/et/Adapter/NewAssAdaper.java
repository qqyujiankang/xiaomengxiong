package com.example.et.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.et.R;
import com.example.et.entnty.Assetswallet;
import com.example.et.fragment.NewWalletFragment;
import com.example.et.util.StringUtils;
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
            Assetswallet assetswallet = (Assetswallet) list.get(position);
            if (assetswallet.getName().equals("USDT")) {
                glideimv(context.getResources().getDrawable(R.mipmap.usdt));

            } else if (assetswallet.getName().equals("DAAS")) {
                glideimv(context.getResources().getDrawable(R.mipmap.daas));
            } else if (assetswallet.getName().equals("HOPE")) {
                glideimv(context.getResources().getDrawable(R.mipmap.hope));
            }

            tvName.setText(assetswallet.getName());
            tvNumber.setText(StringUtils.calculateProfit(Double.valueOf(assetswallet.getNumber()), 5));

        }

        private void glideimv(Drawable drawable) {
            Glide.with(context)
                    .load(drawable)
                    .into(ivView);
        }


    }
}
