package com.example.et.Adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.entnty.Answer;
import com.example.et.util.lifeful.Lifeful;

import java.util.List;

import butterknife.BindView;

public class AnswerAdapter extends ManagerAdapter {
    private int tempPosition = -1;  //记录已经点击的CheckBox的位置

    public AnswerAdapter(Context context, List list, Lifeful lifefu) {
        super(context, list, lifefu);
    }

    @Override
    protected BaseHolder getHolder(Context context) {
        return new ViewHolder();
    }

    public class ViewHolder extends BaseHolder {




        @Override
        protected int getItemLayoutResId() {
            return R.layout.adapter_answer;
        }

        @Override
        public void setViewData(int position, List list) {
            Answer answer = (Answer) list.get(position);
           // tvanser.setText(answer.getString());

        }
    }
}
