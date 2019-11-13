package com.example.et.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.et.R;
import com.example.et.fragment.HeadFragment;

/**
 *
 */
public class MyDiog extends AlertDialog {
    private String string;
    private TextView tv_cone, tv_cone1, tv_compile;
    private Button public_button;
    private int anInt;
    LinearLayout linearLayout, linearLayout1;
    CheckBox checkBox;

    public MyDiog(Context context, String s, int i) {

        super(context);
        this.string = s;
        this.anInt = i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diog_my);
        tv_cone = findViewById(R.id.tv_cone);
        linearLayout = findViewById(R.id.ll_a);
        linearLayout1 = findViewById(R.id.ll_b);
        tv_cone1 = findViewById(R.id.tv_cone1);
        tv_compile = findViewById(R.id.tv_compile);
        checkBox = findViewById(R.id.remember);
        public_button = findViewById(R.id.public_button);
        if (anInt == 0) {

            linearLayout.setVisibility(View.VISIBLE);
            int i = new Double(Double.parseDouble(string)).intValue();
            tv_cone.setText(i + "%");

        } else {

            linearLayout1.setVisibility(View.VISIBLE);
            tv_cone1.setText(getContext().getString(R.string.currency) + ":    " + string);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    HeadFragment.d = true;
                } else {
                    HeadFragment.d = false;
                }

            }
        });
        tv_compile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        public_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
