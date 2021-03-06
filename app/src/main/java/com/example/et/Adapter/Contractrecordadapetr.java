package com.example.et.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.Contractrecord;
import com.example.et.util.CountDown;
import com.example.et.util.LogUtils;
import com.example.et.util.TimeUtils;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

/**
 * 合约记录
 */
public class Contractrecordadapetr extends ManagerAdapter {
    public Contractrecordadapetr(Context context, List list, Lifeful lifefu) {
        super(context, list, lifefu);
    }

    @Override
    protected BaseHolder getHolder(Context context) {
        return new ViewHolder();
    }

    class ViewHolder extends BaseHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_jieidian)
        TextView tvJieidian;
        @BindView(R.id.tv_team)
        TextView tvTeam;
        @BindView(R.id.tv_Active_member)
        TextView tvActiveMember;
        @BindView(R.id.tv_performance)
        TextView tvPerformance;

        @Override
        protected int getItemLayoutResId() {
            return R.layout.adapetr_contractrecord;
        }

        @Override
        public void setViewData(int position, List list) {
            Contractrecord contractrecord = (Contractrecord) list.get(position);
            tvName.setText("USDT");
            tvJieidian.setText(contractrecord.getTimename());
            tvTeam.setText(contractrecord.getNumber());
            tvActiveMember.setText(contractrecord.getTime());
            if (contractrecord.getState() == 0) {
                if (contractrecord.getHour24().equals("null")) {
                    tvPerformance.setText("时间已终止");
                } else {
                    CountDown countDown = new CountDown(tvPerformance,
                            Long.parseLong(contractrecord.getHour24())*1000 - (TimeUtils.getNowMills()), 0);
                    countDown.timerStart();
                }

            } else if (contractrecord.getState() == 1) {
                tvPerformance.setText(R.string.p1);
            } else if (contractrecord.getState() == 3) {
                tvPerformance.setText(R.string.p2);
            }


        }
    }
}
