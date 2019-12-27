package com.example.et.Ustlis;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;

import androidx.fragment.app.Fragment;

import com.example.et.R;
import com.example.et.entnty.ListObject;
import com.example.et.entnty.Transferred;
import com.example.et.fragment.ContractFragment;
import com.example.et.fragment.HeadFragment;
import com.example.et.fragment.MyFragment;
import com.example.et.fragment.NewWalletFragment;
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
     * @param context
     * @return
     */
    public static List getListActivity1(Context context, String s,String string) {
        List<ListObject> listObjects = new ArrayList<>();
        if (s.equals(context.getString(R.string.things_personal))) {
            listObjects.add(new ListObject(context.getString(R.string.top_up), BitmapFactory.decodeResource(context.getResources(), R.mipmap.top_up), null));
            listObjects.add(new ListObject(context.getString(R.string.Mention_money), BitmapFactory.decodeResource(context.getResources(), R.mipmap.mention_money), null));
            listObjects.add(new ListObject(context.getString(R.string.Transferred), BitmapFactory.decodeResource(context.getResources(), R.mipmap.transferred), null));
            listObjects.add(new ListObject(context.getString(R.string.transfer), BitmapFactory.decodeResource(context.getResources(), R.mipmap.transfer), null));
            listObjects.add(new ListObject(context.getString(R.string.record), BitmapFactory.decodeResource(context.getResources(), R.mipmap.record), null));


        } else if (s.equals(context.getString(R.string.Reference_Asset))) {
            listObjects.add(new ListObject(context.getString(R.string.Transferred), BitmapFactory.decodeResource(context.getResources(), R.mipmap.transferred), null));
            if (string.equals("USDT")){
                  listObjects.add(new ListObject(context.getString(R.string.transfer), BitmapFactory.decodeResource(context.getResources(), R.mipmap.transfer), null));
            }

            listObjects.add(new ListObject(context.getString(R.string.record), BitmapFactory.decodeResource(context.getResources(), R.mipmap.record), null));
        }
        return listObjects;
    }


    /**
     * List<Fragment>
     */
    public static ArrayList<Fragment> getListFragmentMainActivity() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(0, new HeadFragment());
        fragments.add(1, new NewWalletFragment());
        fragments.add(2, new ContractFragment());
        fragments.add(3, new MyFragment());

        return fragments;
    }

    public static List getListTransferred(Activity context, int i, String name) {
        List<Transferred> transferreds = new ArrayList<>();
        if (i == 1) {
            transferreds.add(new Transferred("mining", context.getString(R.string.Transferto_mineral_machinery_assets)));
        } else if (i == 2) {
            if (name.equals("USDT")) {
                transferreds.add(new Transferred("dongjie", context.getString(R.string.Assets_to_freeze)));
            } else {
                transferreds.add(new Transferred("mining", context.getString(R.string.Transferto_mineral_machinery_assets)));
                transferreds.add(new Transferred("all", "划转至个人资产"));
            }

        }
        // transferreds.add(new Transferred("all", "划转至个人资产"));

        return transferreds;

    }
}
