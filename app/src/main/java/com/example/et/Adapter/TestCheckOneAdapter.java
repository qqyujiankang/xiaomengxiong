package com.example.et.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.Answer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ListenerGao on 2016/9/12.
 */
public class TestCheckOneAdapter extends BaseAdapter {
    private static final String TAG = "TestCheckOneAdapter";
    private Activity mActivity;
    private List<Answer> mData;

    private int tempPosition = -1;  //记录已经点击的CheckBox的位置

    public TestCheckOneAdapter(Activity context, List<Answer> data) {
        this.mActivity = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size() > 0 ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.adapter_answer, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(mData.get(position).getString());
        holder.checkBox.setId(position);    //设置当前position为CheckBox的id
      if (position == tempPosition)   //比较位置是否一样,一样就设置为选中,否则就设置为未选中.
        {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
        return convertView;
    }

    //返回当前CheckBox选中的位置,便于获取值.
    public int getSelectPosition() {
        return tempPosition;
    }



    public static class ViewHolder {
        @BindView(R.id.tv_name)
        public TextView tvName;
        @BindView(R.id.cb_check)
        public CheckBox checkBox;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
