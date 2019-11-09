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
import com.example.et.util.LogUtils;

/**
 * Created by ywl on 2017/2/28.
 */

public abstract class PayDialog extends AlertDialog {

    private ActivationCodeBox payPwdEditText;
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
        payPwdEditText = (ActivationCodeBox) findViewById(R.id.ppet);
        public_button = findViewById(R.id.public_button);
        tv_cancel = findViewById(R.id.tv_cancel);
        public_button.setText(context.getString(R.string.confirm));
        payPwdEditText.setInputCompleteListener(new ActivationCodeBox.InputCompleteListener() {
            @Override
            public void inputComplete(String code) {
                LogUtils.i("========密码==="+code);

                paypwd = code;
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


//        payPwdEditText.setShowPwd(true);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                payPwdEditText.setFocus();
//            }
//        }, 100);

    }


    public abstract void clickCallBack(String str);

    public abstract void clickBack();

}
