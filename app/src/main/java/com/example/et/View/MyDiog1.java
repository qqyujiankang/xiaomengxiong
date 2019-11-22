package com.example.et.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.et.Activity.LoginActivity;
import com.example.et.R;
import com.example.et.Ustlis.JsonUtils;
import com.example.et.Ustlis.SpUtils;
import com.example.et.entnty.UserData;
import com.example.et.entnty.UserModel;
import com.example.et.util.SharedPreferencesHelper;

import java.util.List;

/**
 *
 */
public class MyDiog1 extends AlertDialog {
    private Button button, button1;
    private Context context;



    private int p;
    private   UserModel userModel;
    private UserModel.UserBean userData;

    public MyDiog1(Context context, UserModel.UserBean userData, UserModel userModel, int p) {

        super(context);
        this.context = context;
        this.userData=userData;
        this.userModel = userModel;
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
                userModel.getData_list().remove(userData);



                String s=  JsonUtils.StrToJson(userModel);

                Log.i("exp_","adapter22===="+s);
                if(s!=null) {
                   SpUtils.putString(context,SpUtils.login,s);
                }



                dismiss();
                LoginActivity.pw.dismiss();
            }
        });
    }
}
