package com.example.et.Adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.et.R;

import java.util.List;

/**
     * 适配器
     *
     * @author Administrator
     */
    public class MyAdapter extends BaseAdapter {
        private Context context;
        private List<SpannableString> carList;

        public MyAdapter(Context context, List<SpannableString> carList) {
            super();
            this.context = context;
            this.carList = carList;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return carList.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            // TODO Auto-generated method stub
            ViewHoler holer = null;
            if (arg1 == null) {
                holer = new ViewHoler();
                arg1 = View.inflate(context, R.layout.items, null);
                holer.tv_name = (TextView) arg1.findViewById(R.id.item);
                arg1.setTag(holer);
            } else {
                holer = (ViewHoler) arg1.getTag();
            }
            holer.tv_name.setText(carList.get(arg0));
            return arg1;
        }

        class ViewHoler {
            private TextView tv_name;
        }
    }