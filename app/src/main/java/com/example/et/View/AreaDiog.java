package com.example.et.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.et.Adapter.AreaAdapter;
import com.example.et.R;
import com.example.et.entnty.Areacode;

import java.util.List;

/**
 * 区号
 */
public class AreaDiog extends AlertDialog {
    private ListView listView;
    private AdapterView.OnItemClickListener onItemClickListener;
    private Context context;
    private List<Areacode> areacodes;

    public AreaDiog(Context context, AdapterView.OnItemClickListener onItemClickListener, List<Areacode> areacodeList) {

        super(context);
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.areacodes = areacodeList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diog_area);
        listView = findViewById(R.id.list_item);
        listView.setAdapter(new AreaAdapter(context, areacodes, null));
        listView.setOnItemClickListener(onItemClickListener);


    }
}
