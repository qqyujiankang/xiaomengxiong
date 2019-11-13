package com.example.et.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.GainRecording;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

/**
 * 收益记录
 */
public class GainRecordingAdapter extends ManagerAdapter {

    public GainRecordingAdapter(Context context, List list, Lifeful lifefu) {
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

        @Override
        protected int getItemLayoutResId() {
            return R.layout.adapter_gain_recording;
        }

        @Override
        public void setViewData(int position, List list) {
            GainRecording gainRecording = (GainRecording) list.get(position);
            tvGlod.setText(gainRecording.getNumber() + "  "+gainRecording.getGlod());
             tvType.setText( gainRecording.getTypes());
            tvHavePeople.setText(gainRecording.getWho());
            tvTime.setText(gainRecording.getTime());

        }
    }
}
