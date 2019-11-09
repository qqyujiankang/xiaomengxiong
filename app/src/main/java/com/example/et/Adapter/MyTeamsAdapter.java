package com.example.et.Adapter;

import android.content.Context;
import android.view.View;
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
        @BindView(R.id.tv_states)
        TextView tvStates;

        @Override
        protected int getItemLayoutResId() {
            return R.layout.adapter_my_teams;
        }

        @Override
        public void setViewData(int position, List list) {
            MyTeam myTeam = (MyTeam) list.get(position);
            tvName.setText(myTeam.getName());
            tvTeam.setText(myTeam.getTeam_pp() + "人");//团队人数
            tvPerformance.setText(myTeam.getAchievement() + "");//业绩
            tvActiveMember.setText(myTeam.getTeam_okpp() + "人");//有效人数
            if (myTeam.getNode() == 1) {
                tvJieidian.setVisibility(View.INVISIBLE);
            } else if (myTeam.getNode() == 2) {
                tvJieidian.setImageDrawable(context.getResources().getDrawable(R.mipmap.node));
            } else if (myTeam.getNode() == 3) {
                tvJieidian.setImageDrawable(context.getResources().getDrawable(R.mipmap.chaojijiedian));
            }
            if (myTeam.getStates() == 1) {
                    tvStates.setText("未激活");
            }else if (myTeam.getStates()==2){
                 tvStates.setText("已激活");
            }


        }
    }
}
