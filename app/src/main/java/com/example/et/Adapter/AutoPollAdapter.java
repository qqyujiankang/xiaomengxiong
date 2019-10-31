package com.example.et.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.et.R;
import com.example.et.entnty.DataFlow;

import java.util.List;

public class AutoPollAdapter extends RecyclerView.Adapter<AutoPollAdapter.BaseViewHolder> {
    private final List<DataFlow> mData;

    public AutoPollAdapter(List<DataFlow> list) {
        this.mData = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_auto_poll, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        DataFlow data = mData.get(position % mData.size());
        holder.tvnumber.setText(data.getName());
        holder.tvmenufind.setText(data.getUsdt());
        holder.tvtime.setText(data.getTime());
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        TextView tvnumber, tvmenufind, tvtime;

        public BaseViewHolder(View itemView) {
            super(itemView);
            tvnumber = itemView.findViewById(R.id.tv_number);
            tvmenufind = itemView.findViewById(R.id.tv_menu_find);
            tvtime = itemView.findViewById(R.id.tv_time);
        }
    }
}
