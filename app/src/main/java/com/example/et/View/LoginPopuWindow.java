package com.example.et.View;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.et.Adapter.Lingondadaper;
import com.example.et.R;
import com.example.et.entnty.UserModel;

public class LoginPopuWindow extends PopupWindow {
    private Context context;
    private UserModel userModel;
    private LinearLayout linearLayout;
    private View inflater;
    private ListView listView;
    Lingondadaper adapter;

    public LoginPopuWindow(Context context, UserModel userModel, AdapterView.OnItemClickListener onItemClickListener) {
        super(context);
        this.context = context;
        this.userModel = userModel;
        LayoutInflater linearLayout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = linearLayout.inflate(R.layout.simple_item, null);
        // 要在这个linearLayout里面找listView......
        listView = (ListView) inflater.findViewById(R.id.lv);
        adapter = new Lingondadaper(context, userModel, null);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
        setListViewHeight(listView);
        this.setContentView(inflater);
        ColorDrawable dw = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(new ColorDrawable());
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(dw);
    }

    private void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter(); //得到ListView 添加的适配器
        if (listAdapter == null) {
            return;
        }

        View itemView = listAdapter.getView(0, null, listView); //获取其中的一项
        //进行这一项的测量，为什么加这一步，具体分析可以参考 https://www.jianshu.com/p/dbd6afb2c890这篇文章
        itemView.measure(0, 0);
        int itemHeight = itemView.getMeasuredHeight(); //一项的高度
        int itemCount = listAdapter.getCount();//得到总的项数
        LinearLayout.LayoutParams layoutParams = null; //进行布局参数的设置
        if (itemCount <= 3) {
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * itemCount);
        } else if (itemCount > 3) {
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * 3);
        }
        listView.setLayoutParams(layoutParams);
    }

}
