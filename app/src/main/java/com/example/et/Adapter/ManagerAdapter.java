package com.example.et.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by ywx on 2018/4/24.
 */

public abstract class ManagerAdapter<T> extends BaseAdapter {


    protected Context context;
    private List<T> tList;
    protected Lifeful lifeful;
    protected LayoutInflater layoutInflater;


    public ManagerAdapter(Context context, List<T> tList, Lifeful lifefu) {
        this.context = context;
        this.tList = tList;
        this.lifeful = lifefu;
        this.layoutInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        if (tList != null && tList.size() > 0) {
            return tList.size();
        } else {
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        if (tList != null && tList.size() > 0) {
            return tList.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        if (tList != null && tList.size() > 0) {
            return position;
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseHolder baseHolder = null;
        int getItemLayoutResId = 0;

        if (convertView == null) {
            baseHolder = getHolder(context);
            if (baseHolder == null) {
                throw new NullPointerException("baseHolder  is  null");
            }
            getItemLayoutResId = baseHolder.getItemLayoutResId();
            if (getItemLayoutResId == 0) {
                throw new NullPointerException("资源文件  is  null");
            }
            convertView = View.inflate(context, getItemLayoutResId, null);
            ButterKnife.bind(baseHolder, convertView);//用butterKnife绑定
            convertView.setTag(baseHolder);

        } else {
            baseHolder = (BaseHolder) convertView.getTag();
        }

        baseHolder.setViewData(position, tList);//将数据传给holder

        return convertView;

    }


    /**
     * 返回对应的holder类
     *
     * @param context 引用
     * @return 返回对应的holder子类，需要继承自BaseHolder
     */
    protected abstract BaseHolder getHolder(Context context);




     abstract class BaseHolder {

         /**
          * @return 返回布局的资源文件id
          */
         protected abstract int getItemLayoutResId();
        /**
         * 设置要显示的数据
         *
         * @param tList     数据，转换成对应的数据对象
         * @param position 当前item的位置
         */
        public abstract void setViewData(int position, List<T> tList);

    }

}
