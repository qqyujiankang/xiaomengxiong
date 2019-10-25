package com.example.et.util.realize;

import android.content.Context;
import android.widget.GridView;
import android.widget.ListView;

import com.example.et.entnty.Pagebean;
import com.example.et.util.lifeful.Lifeful;


/**
 * Created by Administrator on 2017/12/11 0011.
 */

public interface AdapterModel {



    Integer AdapterSetListDatas(Pagebean<Object> objectPagebean, Integer page, ListView listView, Context mContext, Class<?> cls, Lifeful lifeful);
    Integer AdapterSetListDatas1(Pagebean<Object> objectPagebean, Integer page, GridView listView, Context mContext, Class<?> cls);
   // void AdapterSetListDatas(List<Object> objectList, @LayoutRes int adapter_layout, GridView gridView, Context mContext);

}
