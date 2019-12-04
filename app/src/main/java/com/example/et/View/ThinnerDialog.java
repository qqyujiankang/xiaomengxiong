package com.example.et.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.et.R;
import com.example.et.entnty.VersionInfo;

import static com.example.et.Ustlis.ActivityUtils.startActivity;

/**
 * 更新的对话框
 */
public class ThinnerDialog extends AlertDialog {
    private Context context;
    private VersionInfo versionInfo;
    private Button button;
    private TextView textView, tv_version_number;

    public ThinnerDialog(@NonNull Context context, VersionInfo usVersionInfo) {
        super(context);
        this.context = context;
        this.versionInfo = usVersionInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_thinner);
        textView = findViewById(R.id.tv_text);
        tv_version_number = findViewById(R.id.tv_version_number);
        tv_version_number.setText(versionInfo.getData().getAndroid());
        textView.setText(versionInfo.getData().getText().replace("/n","\n"));
        button = findViewById(R.id.btn_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(versionInfo.getData().getAndroidurl());
                intent.setData(content_url);
                startActivity(intent);
            }
        });
    }
}
