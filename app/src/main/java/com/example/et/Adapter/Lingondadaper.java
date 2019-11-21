package com.example.et.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.et.Activity.LoginActivity;
import com.example.et.R;
import com.example.et.View.MyDiog;
import com.example.et.View.MyDiog1;
import com.example.et.entnty.UserData;
import com.example.et.util.SharedPreferencesHelper;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;


public class Lingondadaper extends ManagerAdapter {


    public Lingondadaper(Context context, List<UserData> list, Lifeful lifefu) {
        super(context, list, lifefu);
    }

    @Override
    protected BaseHolder getHolder(Context context) {
        return new ViewHolder();
    }


    public class ViewHolder extends BaseHolder {

        @BindView(R.id.item)
        TextView item;
        @BindView(R.id.tv_)
        TextView tv;


        @Override
        protected int getItemLayoutResId() {
            return R.layout.items;
        }

        @Override
        public void setViewData(int position, List list) {
            UserData userData= (UserData) list.get(position);
            item.setText(userData.getAcount());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new MyDiog1(context,userData.getAnInt(), list,position).show();


                }
            });


        }



    }


}
