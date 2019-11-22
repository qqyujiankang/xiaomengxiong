package com.example.et.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.Ustlis.JsonUtils;
import com.example.et.View.MyDiog1;
import com.example.et.entnty.UserModel;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;


public class Lingondadaper extends ManagerAdapter {
    private UserModel userModel;


    public Lingondadaper(Context context, UserModel userModel, Lifeful lifefu) {

        super(context, userModel.getData_list(), lifefu);
        this.userModel=userModel;
        String s=  JsonUtils.StrToJson(userModel);
        Log.i("exp_","adapter11===="+s);
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


            UserModel.UserBean userData= (UserModel.UserBean) list.get(position);
            item.setText(userData.getPhoneOremail());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    new MyDiog1(context,userData, userModel,position).show();


                }
            });


        }



    }


}
