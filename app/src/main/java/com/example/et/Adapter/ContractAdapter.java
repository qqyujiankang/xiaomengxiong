package com.example.et.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.et.Activity.AppointmentcontractActivity;
import com.example.et.Activity.HopePropertyActivityActivity;
import com.example.et.Activity.PersonalAssetsActivity;
import com.example.et.Activity.TransferActivity;
import com.example.et.Activity.TransferredActivity;
import com.example.et.R;
import com.example.et.entnty.Contract;
import com.example.et.entnty.Currencyaddress;
import com.example.et.entnty.Transferred;
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
            if (context instanceof AppointmentcontractActivity) {
                Contract contract = (Contract) list.get(position);
                tvName.setText(contract.getNumber() + "USDT");
            } else if (context instanceof HopePropertyActivityActivity) {
                Currencyaddress currencyaddress = (Currencyaddress) list.get(position);
                tvName.setText(currencyaddress.getString());
            } else if (context instanceof PersonalAssetsActivity) {
                Currencyaddress currencyaddress = (Currencyaddress) list.get(position);
                tvName.setText(currencyaddress.getString());
            } else if (context instanceof TransferredActivity) {
                Transferred transferred = (Transferred) list.get(position);
                tvName.setText(transferred.getName());

            }
        }
    }
}
