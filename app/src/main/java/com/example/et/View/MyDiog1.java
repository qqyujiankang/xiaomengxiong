package com.example.et.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.et.Activity.LoginActivity;
import com.example.et.R;
import com.example.et.fragment.HeadFragment;
import com.example.et.util.SharedPreferencesHelper;

/**
 *
 */
public class MyDiog1 extends AlertDialog {
    private Button button, button1;
    private Context context;
    SharedPreferencesHelper helper;
    private String string;
    private int id;

    public MyDiog1(Context context, String string, int position) {

        super(context);
        this.context = context;
        this.id = position;

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
                helper = new SharedPreferencesHelper(context, "user");
                helper.remove("name" + id);
                dismiss();
                LoginActivity.pw.dismiss();
            }
        });
    }
}
