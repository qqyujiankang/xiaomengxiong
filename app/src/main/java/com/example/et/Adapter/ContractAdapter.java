package com.example.et.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.Contract;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

/**
 * 选择币种的对话框
 */
public class ContractAdapter extends ManagerAdapter {
    public ContractAdapter(Context context, List list, Lifeful lifefu) {
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
            return R.layout.adapter_contract;
        }

        @Override
        public void setViewData(int position, List list) {
            Contract contract = (Contract) list.get(position);
            tvName.setText(contract.getNumber() + "USTD");
        }
    }
}
