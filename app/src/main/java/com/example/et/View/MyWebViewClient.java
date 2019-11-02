package com.example.et.View;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    //   private WaitingDialog dialog;
    private Activity activity;

    public MyWebViewClient(Activity activity) {
        // dialog = new WaitingDialog(activity);
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //ogUtil.e(url);
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (!activity.isFinishing()) {
            //  dialog.show();
        }
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
        super.onReceivedSslError(view, handler, error);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        view.loadUrl("javascript:function getSub(){alert(\"Welcome\");" + "document.forms[0].submit();};getSub();");
        view.loadUrl("javascript:function getSub(){" +
                "document.getElementsByTagName('body')[0].style.background='#15243B'" +
                "};getSub();");

    }


}