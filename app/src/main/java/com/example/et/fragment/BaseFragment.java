package com.example.et.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.et.R;
import com.example.et.util.BarUtils;
import com.example.et.util.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BaseFragment#} factory method to
 * create an instance of this fragment.
 */
public abstract class BaseFragment extends Fragment {


    protected boolean isInit = false;
    protected boolean isLoad = false;
    protected final String TAG = "BaseFragment";
    private View view;
    Unbinder unbinder;








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            LogUtils.i("==========="+view);
            view = inflater.inflate(setContentView(), container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        // isInit = true;
        /**初始化的时候去加载数据**/
        initView();
        // isCanLoadData();

        return view;
    }





    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    protected abstract int setContentView();

    /**
     * 页面 初始化布局
     */
    public void initView() {

    }

    ;

    public void getIntentDatas() {
    }

    /**
     * 请求数据
     */
    public void requestDatas() {

    }

    ;

    public void requestDatas2() {

    }

    ;

    public void requestDatas3() {

    }
    public void requestDatas4() {

    }



}
