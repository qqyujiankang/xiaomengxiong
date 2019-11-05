package com.example.et.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.Areacode;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

public class AreaAdapter extends ManagerAdapter {
    public AreaAdapter(Context context, List list, Lifeful lifefu) {
        super(context, list, lifefu);
    }

    @Override
    protected BaseHolder getHolder(Context context) {
        return new ViewHolder();
    }

    class ViewHolder extends BaseHolder {

        @BindView(R.id.tv_name)
        TextView tvName;

        @Override
        protected int getItemLayoutResId() {
            return R.layout.adapter_area;
        }

        @Override
        public void setViewData(int position, List list) {
            Areacode areacode= (Areacode) list.get(position);
            tvName.setText("+"+areacode.getMobile_prefix());

        }
    }
}
