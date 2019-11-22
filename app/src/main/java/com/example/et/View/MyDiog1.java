package com.example.et.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.et.Activity.LoginActivity;
import com.example.et.R;
import com.example.et.Ustlis.SpUtils;
import com.example.et.entnty.UserData;
import com.example.et.util.SharedPreferencesHelper;

import java.util.List;

/**
 *
 */
public class MyDiog1 extends AlertDialog {
    private Button button, button1;
    private Context context;
    private UserData userData;
    private int id;
    private int p;
    private List<UserData> userDataList;

    public MyDiog1(Context context, int position, List<UserData> userData, int p) {

        super(context);
        this.context = context;
        this.id = position;
        this.userDataList = userData;
        this.p = p;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diog1_my);
        button = findViewById(R.id.public_button);
        button1 = findViewById(R.id.public_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SpUtils.remove(context, "name" + id);
                SpUtils.remove(context, "pwd" + id);
                SpUtils.remove(context, "tepy" + id);

                userDataList.remove(p);
                dismiss();
                LoginActivity.pw.dismiss();
            }
        });
    }
}
