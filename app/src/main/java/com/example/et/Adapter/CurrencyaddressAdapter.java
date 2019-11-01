package com.example.et.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.Currencyaddress;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

public class CurrencyaddressAdapter extends ManagerAdapter {
    public CurrencyaddressAdapter(Context context, List list, Lifeful lifefu) {
        super(context, list, lifefu);
    }

    @Override
    protected BaseHolder getHolder(Context context) {
        return new ViewHolder();
    }

    class ViewHolder extends BaseHolder {

        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_address_binding)
        TextView tvAddressBinding;
        @BindView(R.id.tv_compile)
        TextView tvCompile;

        @Override
        protected int getItemLayoutResId() {
            return R.layout.adapter_currency_address;
        }

        @Override
        public void setViewData(int position, List list) {
            Currencyaddress currencyaddress = (Currencyaddress) list.get(position);
            tvAddress.setText(currencyaddress.getStringname() + "地址");
            tvAddressBinding.setText(currencyaddress.getString());

        }
    }
}
