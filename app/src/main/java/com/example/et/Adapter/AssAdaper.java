package com.example.et.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.Ass;
import com.example.et.util.StringUtils;
import com.example.et.util.lifeful.Lifeful;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;

/**
 * 资产列表Adaper
 */
public class AssAdaper extends ManagerAdapter {
    public AssAdaper(Context context, List list, Lifeful lifefu) {
        super(context, list, lifefu);
    }

    @Override
    protected BaseHolder getHolder(Context context) {
        return new ViewHolder();
    }

    class ViewHolder extends BaseHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv_bank)
        ImageView ivBank;
        @BindView(R.id.textView3)
        TextView textView3;
        @BindView(R.id.RL_Usdt)
        RelativeLayout RLUsdt;
        @BindView(R.id.Rl_banl)
        RelativeLayout RlBanl;
        @BindView(R.id.Rl_ban2)
        RelativeLayout RlBan2;
        @BindView(R.id.RL_Hope)
        RelativeLayout RLHope;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_nameHOPE)
        TextView tvNameHOPE;
        @BindView(R.id.tv_balance)
        TextView tvBalance;
        @BindView(R.id.tv_make)
        TextView tvMake;
        @BindView(R.id.tv_convert_init)
        TextView tvConvertInit;


        @Override
        protected int getItemLayoutResId() {
            return R.layout.adaper_ass;
        }

        @Override
        public void setViewData(int position, List list) {
            Ass ass = (Ass) list.get(position);
            if (ass.getId() == 8) {
                RLHope.setVisibility(View.GONE);
                tvName.setText(ass.getName());
                tvNumber.setText(StringUtils.calculateProfit(Double.valueOf(ass.getNumber()),5));
            } else if (ass.getId() == 7) {
                tvNameHOPE.setText(ass.getName());
                RLUsdt.setVisibility(View.GONE);
                tvBalance.setText(StringUtils.calculateProfit(Double.valueOf(ass.getNumber()),5));
                tvMake.setText(StringUtils.calculateProfit(Double.valueOf(ass.getNewnumber()),5));
                tvConvertInit.setText(StringUtils.calculateProfit(Double.valueOf(ass.getCny()),5));

            }
        }
    }


}
