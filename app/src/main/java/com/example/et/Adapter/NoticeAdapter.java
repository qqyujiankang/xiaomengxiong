package com.example.et.Adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.Announcement;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

/**
 * 公告的Adapter
 */
public class NoticeAdapter extends ManagerAdapter {
    public NoticeAdapter(Context context, List list, Lifeful lifefu) {
        super(context, list, lifefu);
    }

    @Override
    protected BaseHolder getHolder(Context context) {
        return new ViewHolder();
    }

    class ViewHolder extends BaseHolder {

        @BindView(R.id.islook)
        CheckBox islook;
        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_time)
        TextView tvTime;

        @Override
        protected int getItemLayoutResId() {
            return R.layout.adapter_notice;
        }

        @Override
        public void setViewData(int position, List list) {
            Announcement announcement = (Announcement) list.get(position);
            if (announcement.getIslook() == 2) {
                islook.setChecked(true);
            } else {
                islook.setChecked(false);
            }
            tvName.setText(announcement.getName());
            tvTime.setText(announcement.getTime());

        }
    }
}
