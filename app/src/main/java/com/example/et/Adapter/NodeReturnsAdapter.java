package com.example.et.Adapter;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.NodeReturns;
import com.example.et.util.CacheUtils;
import com.example.et.util.StringUtils;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

public class NodeReturnsAdapter extends ManagerAdapter {
    public NodeReturnsAdapter(Context context, List list, Lifeful lifefu) {
        super(context, list, lifefu);
    }

    @Override
    protected BaseHolder getHolder(Context context) {
        return new ViewHolder();
    }

    class ViewHolder extends BaseHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_total)
        TextView tvTotal;
        @BindView(R.id.tv_convertinto)
        TextView tvConvertinto;

        @Override
        protected int getItemLayoutResId() {
            return R.layout.adapter_node_returns;
        }

        @Override
        public void setViewData(int position, List list) {
            NodeReturns nodeReturns = (NodeReturns) list.get(position);
            tvName.setText(nodeReturns.getName());

            //String str2 = context.getString(R.string.convert)+"<font color='#ffffff'>"+"    "+StringUtils.calculateProfit(Double.parseDouble(nodeReturns.getConvertinto()), 5)+"</font>";
            tvTotal.setText(StringUtils.calculateProfit(Double.parseDouble(nodeReturns.getTotal()), 5));
            tvConvertinto.setText(StringUtils.calculateProfit(Double.parseDouble(nodeReturns.getConvertinto()), 5));


        }
    }
}
