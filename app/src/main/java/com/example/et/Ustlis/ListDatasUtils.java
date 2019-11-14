package com.example.et.Ustlis;

import android.content.Context;
import android.graphics.BitmapFactory;

import androidx.fragment.app.Fragment;

import com.example.et.R;
import com.example.et.entnty.ListObject;
import com.example.et.fragment.ContractFragment;
import com.example.et.fragment.HeadFragment;
import com.example.et.fragment.MyFragment;
import com.example.et.fragment.WalletFragment;

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

    /**
     *
     * List<Fragment>
     */
    public static ArrayList<Fragment> getListFragmentMainActivity() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(0, new HeadFragment());
        fragments.add(1, new WalletFragment());
        fragments.add(2, new ContractFragment());
        fragments.add(3, new MyFragment());

        return fragments;
    }

}
