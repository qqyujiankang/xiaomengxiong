package com.example.et.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.Record;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

/**
 * 资产记录
 */
public class RecordAdapter extends ManagerAdapter {
    public RecordAdapter(Context context, List list, Lifeful lifefu) {
        super(context, list, lifefu);
    }

    @Override
    protected BaseHolder getHolder(Context context) {
        return new ViewHolder();
    }

    class ViewHolder extends BaseHolder {


        @BindView(R.id.tv_glod)
        TextView tvGlod;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_Have_people)
        TextView tvHavePeople;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_)
        TextView tv;
        @BindView(R.id.tv_tophone)
        TextView tvTophone;
        @BindView(R.id.lvphone)
        LinearLayout lvphone;

        @Override
        protected int getItemLayoutResId() {

            return R.layout.adapter_record;
        }

        @Override
        public void setViewData(int position, List list) {

            Record record = (Record) list.get(position);
            tvTime.setText(record.getTime());
            tvType.setText(record.getTypes());
            tvHavePeople.setText(record.getType());
            if (record.getTophone()!=null) {
                lvphone.setVisibility(View.VISIBLE);
                tvTophone.setText(record.getTophone());
            }else {
                lvphone.setVisibility(View.GONE);
            }


            if (Double.parseDouble(record.getNumber()) > 0) {
                tvGlod.setText("+" + record.getNumber());
                tvGlod.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            } else if (Double.parseDouble(record.getNumber()) < 0) {
                tvGlod.setText(record.getNumber());
                tvGlod.setTextColor(context.getResources().getColor(R.color.colorAccent_01));
            }
            if (list.size() - 1 == position) {
                tv.setVisibility(View.GONE);
            }


        }
    }
}
