package com.example.et.util;
/*
 * 包名       com.xuyj.android.notification.channel
 * 文件名:    NotificationHelper
 * 创建者:    xuyj
 * 创建时间:  2018/3/16 on 10:36
 * 描述:     TODO
 */

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import com.example.et.Activity.SplashActivity;
import com.example.et.R;


public class NotificationHelper extends ContextWrapper {
    private NotificationManager mNotificationManager;
 //  private NotificationChannel mNotificationChannel;

    public static final String CHANNEL_ID = "1001";
    private static final String CHANNEL_NAME = "Default Channel";
 //   private static final String CHANNEL_DESCRIPTION = "this is default channel!";

    public NotificationHelper(Context base) {
        super(base);
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationChannel.setDescription(CHANNEL_DESCRIPTION);
            getNotificationManager().createNotificationChannel(mNotificationChannel);
        }*/
    }

    /**
     * @param title   上部标题
     * @param content 中部通知内容
     */
    public NotificationCompat.Builder getNotification(String title, String content, Class aClass, String url) {
        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
            builder.setChannelId(CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(this);

        }
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentTitle(title);//上部标题
        builder.setContentText(content);//中部通知内容
        builder.setDefaults(Notification.DEFAULT_ALL);//通知的声音震动等都随系统
        //也可以选择使用声音文件，这里的文件是res/raw/miui_notice.mp3
        //        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.miui_notice);
        //        builder.setSound(uri);
        builder.setSmallIcon(R.mipmap.back);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.baodan));
        PendingIntent pendingIntent = null;
        Intent intent = new Intent();
        if (aClass != null) {
            /*
             * 跳转页面
             *
             * */
            intent.setClass(this, aClass);
        } else if (url != null) {
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);

        } else {

            intent.setClass(this, SplashActivity.class);

        }
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);
        //点击自动删除通知
        builder.setAutoCancel(true);
        return builder;
    }

    public void notify(int id, NotificationCompat.Builder builder) {
        if (getNotificationManager() != null) {
            getNotificationManager().notify(id, builder.build());
        }
    }

    public void openChannelSetting(String channelId) {
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null)
            startActivity(intent);
    }

    public void openNotificationSetting() {
        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null)
            startActivity(intent);
    }

    private NotificationManager getNotificationManager() {
        if (mNotificationManager == null)
            mNotificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
        return mNotificationManager;
    }

}
