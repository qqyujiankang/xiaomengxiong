package com.example.et.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.et.Adapter.ContractAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ListDatasUtils;
import com.example.et.entnty.Contract;
import com.example.et.entnty.Currencyaddress;
import com.example.et.entnty.Pagebean;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.AdapterRealize;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by Administrator on 2018/3/29.
 */

public class AlipayPopuWindow extends PopupWindow {
    private View mMenuView;
    private ListView listView;
    private AdapterRealize adapterRealize;
    private TextView tv_cancel, tvtite;

    public AlipayPopuWindow(final Activity context, AdapterView.OnItemClickListener itemsOnClick, Lifeful Lifeful, List<Currencyaddress> currencyaddresses, int i,String name) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popup_window_alipay, null);


        /**
         *
         */


        listView = mMenuView.findViewById(R.id.listv);
        tv_cancel = mMenuView.findViewById(R.id.tv_cancel);
        tvtite = mMenuView.findViewById(R.id.tv_tite);

        listView.setOnItemClickListener(itemsOnClick);
        if (Lifeful != null) {
            tvtite.setText(R.string.Select_reservation_amount);
            retdata(Lifeful, context, listView);

        } else {
            if (i == 0) {
                tvtite.setText(R.string.select);
                listView.setAdapter(new ContractAdapter(context, currencyaddresses, Lifeful));
            } else {

                tvtite.setVisibility(View.GONE);
                listView.setAdapter(new ContractAdapter(context, ListDatasUtils.getListTransferred(context,i,name), Lifeful));
            }

        }
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);

        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //  this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    /**
     * @param lifeful
     * @param context
     * @param listView
     */
    private void retdata(Lifeful lifeful, Activity context, ListView listView) {

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            TaskPresenterUntils.lifeful(Constant.contract, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("=======lifeful====" + success);
                    if (adapterRealize == null) {
                        adapterRealize = new AdapterRealize();
                    }

                    Pagebean<Object> objectPagebean = ParseUtils.analysisListTypeDatasAndCount(context, success, Contract[].class, false);

                    adapterRealize.AdapterSetListDatas(objectPagebean, 0, listView, context, ContractAdapter.class, lifeful);//返回第几页


                }

            }, lifeful));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
