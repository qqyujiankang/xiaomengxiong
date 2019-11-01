package com.example.et.Adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.MyTeam;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

/**
 * 我的团队
 */
public class MyTeamsAdapter extends ManagerAdapter {
    public MyTeamsAdapter(Context context, List list, Lifeful lifefu) {
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
        ImageView tvJieidian;
        @BindView(R.id.tv_team)
        TextView tvTeam;
        @BindView(R.id.tv_Active_member)
        TextView tvActiveMember;
        @BindView(R.id.tv_performance)
        TextView tvPerformance;

        @Override
        protected int getItemLayoutResId() {
            return R.layout.adapter_my_teams;
        }

        @Override
        public void setViewData(int position, List list) {
            MyTeam myTeam = (MyTeam) list.get(position);
            tvName.setText(myTeam.getName() + "(" + myTeam.getPhone() + ")");
            tvTeam.setText(myTeam.getTeam_pp()+"人");//团队人数
            tvPerformance.setText(myTeam.getAchievement()+"人");//业绩
            tvActiveMember.setText(myTeam.getTeam_okpp()+"人");//有效人数

        }
    }
}
