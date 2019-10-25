package com.example.et.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.et.R;

/**
 * 照相机和相册
 * Created by Administrator on 2018/3/12.
 */

public abstract class PubliccAlertDialog extends AlertDialog {
    private Context context;
    private boolean aBoolean;

    public PubliccAlertDialog(Context context) {
        super(context);
        this.context = context;
    }

    protected PubliccAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected PubliccAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private Button btnPhoto, btnCapture, btnCanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window_button);
        btnCanel = (Button) findViewById(R.id.btnCanel);
        btnPhoto = (Button) findViewById(R.id.btnPhoto);//相册
        btnCapture = (Button) findViewById(R.id.btnCapture);//照相机
        btnCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickCallBack();
                dismiss();

            }
        });
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                clickBack();
                dismiss();
            }
        });


    }

    public abstract void clickCallBack();

    public abstract void clickBack();


}

