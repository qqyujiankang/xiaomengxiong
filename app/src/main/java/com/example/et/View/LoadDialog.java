package com.example.et.View;

/**
 * Created by ywx on 2018/3/31.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.et.R;
import com.example.et.Ustlis.ScreenUtils;


/**
 * [进度加载对话框]
 *
 * @author: mashidong
 * @version: 1.0
 * @date: 2015-9-4
 */
public class LoadDialog extends Dialog {

    /**
     * LoadDialog
     */
    private static LoadDialog loadDialog;
    /**
     * cancelable, the dialog dimiss or undimiss flag
     */
    private boolean cancelable;
    /**
     * if the dialog don't dimiss, what is the tips.
     */
    private String tipMsg;

    /**
     * the LoadDialog constructor
     *
     * @param ctx        Context
     * @param cancelable boolean
     * @param tipMsg     String
     */

    public LoadDialog(final Context ctx, boolean cancelable, String tipMsg) {
        super(ctx);

        this.cancelable = cancelable;
        this.tipMsg = tipMsg;

        this.getContext().setTheme(R.style.LunchTheme);
        setContentView(R.layout.public_dialog_layout);
        // 必须放在加载布局后
        setparams();
        TextView tv = (TextView) findViewById(R.id.tvLoad);
        if (!TextUtils.isEmpty(tipMsg)) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(tipMsg);
        }
    }

    private void setparams() {
        this.setCancelable(cancelable);
        this.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        // Dialog宽度
        lp.width = (int) (ScreenUtils.getScreenWidth() * 0.7);
        Window window = getWindow();
      //  window.setBackgroundDrawable(new ColorDrawable(Color.RED));
        window.setAttributes(lp);
        window.getDecorView().getBackground().setAlpha(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!cancelable) {
                Toast.makeText(getContext(), tipMsg, Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * show the dialog
     *
     * @param context
     */
    public static void show(Context context) {
        show(context, null, true);
    }

    /**
     * show the dialog
     *
     * @param context Context
     * @param message String
     */
    public static void show(Context context, String message) {
        show(context, message, true);
    }

    /**
     * show the dialog
     *
     * @param context    Context
     * @param resourceId resourceId
     */
    public static void show(Context context, int resourceId) {
        show(context, context.getResources().getString(resourceId), true);
    }

    /**
     * show the dialog
     *
     * @param context    Context
     * @param message    String, show the message to user when isCancel is true.
     * @param cancelable boolean, true is can't dimiss，false is can dimiss
     */
    private static void show(Context context, String message, boolean cancelable) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (loadDialog != null && loadDialog.isShowing()) {
            return;
        }
        try {
            loadDialog = new LoadDialog(context, cancelable, message);
            loadDialog.show();
        } catch (Exception e) {
            loadDialog = null;
        }

    }

    /**
     * dismiss the dialog
     */
    public static void dismiss(Context context) {
        try {
            if (context instanceof Activity) {
                if (((Activity) context).isFinishing()) {
                    loadDialog = null;
                    return;
                }
            }

            if (loadDialog != null && loadDialog.isShowing()) {
                Context loadContext = loadDialog.getContext();
                if (loadContext != null && loadContext instanceof Activity) {
                    if (((Activity) loadContext).isFinishing()) {
                        loadDialog = null;
                        return;
                    }
                }
                loadDialog.dismiss();
                loadDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            loadDialog = null;
        }
    }
}
