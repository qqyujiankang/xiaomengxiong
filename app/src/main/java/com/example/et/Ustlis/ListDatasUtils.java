package com.example.et.Ustlis;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.et.R;
import com.example.et.entnty.ListObject;

import java.util.ArrayList;
import java.util.List;

public class ListDatasUtils {
    private ListDatasUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * @param context
     * @return
     */
    public static List getListActivity(Context context) {
        List<ListObject> listObjects = new ArrayList<>();
        listObjects.add(new ListObject(context.getString(R.string.Appointment_contract), BitmapFactory.decodeResource(context.getResources(), R.mipmap.baodan), null));
        listObjects.add(new ListObject(context.getString(R.string.To_perform_the_contract), BitmapFactory.decodeResource(context.getResources(), R.mipmap.to_perform_the_contract), null));
        listObjects.add(new ListObject(context.getString(R.string.Contract_record), BitmapFactory.decodeResource(context.getResources(), R.mipmap.jilu), null));
        return listObjects;
    }
}
