package com.example.et.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.R;

/**
 * Created by ywl on 2017/2/28.
 */

public abstract class PayDialog extends AlertDialog {

    private PayPwdEditText payPwdEditText;
    private Context context;
    private Button public_button;
    private TextView tv_cancel;


    public PayDialog(Context context) {
        super(context);
        this.context = context;

    }

    protected PayDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    private String paypwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_dialog_lyaout);
        payPwdEditText = (PayPwdEditText) findViewById(R.id.ppet);
        public_button = findViewById(R.id.public_button);
        tv_cancel = findViewById(R.id.tv_cancel);
        public_button.setText(context.getString(R.string.confirm));
        payPwdEditText.initStyle(R.color.p_green_, 6, 10.00f, R.color.black_01, android.R.color.black, 20);
        payPwdEditText.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {//密码输入完后的回调
                paypwd = str;

            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        public_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallBack(paypwd);
                dismiss();
            }
        });


        payPwdEditText.setShowPwd(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                payPwdEditText.setFocus();
            }
        }, 100);

    }


    public abstract void clickCallBack(String str);

    public abstract void clickBack();

}
