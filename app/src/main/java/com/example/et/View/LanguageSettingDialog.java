package com.example.et.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.et.Activity.LoginActivity;
import com.example.et.R;
import com.example.et.Ustlis.internationalization.LocalManageUtil;
import com.example.et.util.CacheUtils;
import com.example.et.util.constant.CacheConstants;


/***
 * 国际化语言设置
 */
public class LanguageSettingDialog extends Dialog {
    private TextView mUserSelect;
    Context context;
    public Activity activity;

    public LanguageSettingDialog(@NonNull Context context, Activity activity) {
        super(context, R.style.DialogBottomStyle);
        setContentView(R.layout.dialog_language_setting);
        this.context = context;
        this.activity = activity;
        initValues();
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.DialogBottomAnimationStyle); //添加上下滑动动画
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }


    private void initValues() {
//        mUserSelect = findViewById(R.id.tv_user_select);
//        //TODO 当前选择的语言
//        mUserSelect.setText(context.getString(R.string.user_select_language, LocalManageUtil.getSelectLanguage(context)));
        //
        setClick();
    }

    private void setClick() {
        findViewById(R.id.btn_traditional).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLanguage(1);
            }
        });
        findViewById(R.id.btn_cn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLanguage(2);
            }
        });
        findViewById(R.id.btn_en).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLanguage(3);

            }
        });
    }


    private void selectLanguage(int select) {
        LocalManageUtil.saveSelectLanguage(context, select);
        if (activity instanceof LoginActivity) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        //保存选择语言吧到本地
        String language = LocalManageUtil.getSelectLanguage(context);
        if (language.equals("ENGLISH")) {//英语  @"en"
            CacheUtils.getInstance().put(CacheConstants.Lang, "en");
        } else if (language.equals("简体中文")) {
            CacheUtils.getInstance().put(CacheConstants.Lang, "zh-Hans");
        } else if (language.equals("繁體中文")) {
            CacheUtils.getInstance().put(CacheConstants.Lang, "zh-Hant");
        }

    }


}
