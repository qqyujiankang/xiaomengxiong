package com.example.et.Adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.et.Activity.PersonalDataActivity;
import com.example.et.R;
import com.example.et.entnty.ListObject;
import com.example.et.util.lifeful.Lifeful;
import com.just.agentweb.LogUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ywx on 2018/4/17.
 */

public class ListActivitynewAdapter<T> extends ManagerAdapter {
    public ListActivitynewAdapter(Context context, List list, Lifeful lifeful) {
        super(context, list, lifeful);
    }

    @Override
    protected BaseHolder getHolder(Context context) {
        return new ViewHolder();
    }


    class ViewHolder extends BaseHolder {
        @BindView(R.id.image_view)
        ImageView imageView;
        @BindView(R.id.text_view)
        TextView textView;

        @Override
        protected int getItemLayoutResId() {

            return R.layout.adapter_new_list_object;

        }

        @Override
        public void setViewData(int position, List tList) {

            ListObject listObject = (ListObject) tList.get(position);
            textView.setText(listObject.getName());
            imageView.setImageBitmap(listObject.getImage_name());
//            Glide.with(context)
//                    .load(JniUrl.getHttpUrl(0) + listObject.getImage_name() + ".png")
//                    .into(imageView);
        }
    }

}
