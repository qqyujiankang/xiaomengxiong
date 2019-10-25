package com.example.et.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.et.R;
import com.example.et.util.constant.CacheConstants;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by ywx on 2018/9/3.
 */

public class BadgeUtil {

     static final String CHANNEL_ID = "default";
    private BadgeUtil() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    private final static String HUAWEI = "Huawei";
    private final static String HONOR = "HONOR";//华为NOVA

    private final static String MEIZU = "Meizu";
    private final static String XIAOMI = "Xiaomi";
    private final static String SONY = "Sony";//索尼
    private final static String OPPO = "OPPO";
    private final static String VIVO = "vivo";
    private final static String SAMSUNG = "samsung";
    private final static String LG = "LG";
    private final static String ZUK = "ZUK";
    private final static String HTC = "HTC";
    private final static String NOVA = "NOVA";
    private final static String LETV = "Letv";
    private final static String ZTE = "ZTE";
    private final static String YULONG = "YuLong";
    private final static String LENOVO = "LENOVO";

    /**
     * 设置Badge 目前支持Launcher:
     * <p/>
     * MIUI
     * Sony
     * Samsung
     * LG
     * HTC
     * Nova
     *
     * @param context context
     */
    public static void setBadgeCount(Context context) {
        // TODO 生成器模式重构
      int count=0;
        try {
           count= Integer.parseInt(CacheUtils.getInstance().getString(CacheConstants.messageNo));
           count=count<=0?0: Math.max(0, Math.min(count, 99));
        }catch (Exception e){
           count=0;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase(XIAOMI)) {//小米
            setBadgeOfXIAOMI(context, count);
        } else if (Build.MANUFACTURER.equalsIgnoreCase(SONY)) {     //索尼
            setBadgeOfSony(context, count);
        } else if (Build.MANUFACTURER.equalsIgnoreCase(SAMSUNG) || Build.MANUFACTURER.equalsIgnoreCase(LG)) { //三星和LG
            setBadgeOfSumsung(context, count);
        } else if (Build.MANUFACTURER.equalsIgnoreCase(HTC)) {//  HTC
            setBadgeOfHTC(context, count);
        } else if (Build.MANUFACTURER.equalsIgnoreCase(NOVA)) {// NOVA
            setBadgeOfNova(context, count);
        }else if(Build.MANUFACTURER.equalsIgnoreCase(HUAWEI)|| Build.MANUFACTURER.equalsIgnoreCase(HONOR)){//  测试通过
            setBadgeOfHuaWei(context, count);
        }else if(Build.MANUFACTURER.equalsIgnoreCase(OPPO)){//  OPPO
            setBadgeOfOPPO(context, count);
        }else if(Build.MANUFACTURER.equalsIgnoreCase(VIVO)){//   VIVO
            setBadgeOfVIVO(context,count);
        } else if(Build.MANUFACTURER.equalsIgnoreCase(ZUK)){//  ZUK 华硕
            setBadgeOfZUK(context,count);
        }else  {
            setBadgeOfDefault(context,count);//其他
        }

    }

    private static void setBadgeOfDefault(Context context, int count) {
        try {
            Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            intent.putExtra("badge_count", count);
            intent.putExtra("badge_count_package_name", context.getPackageName());
            intent.putExtra("badge_count_class_name", getLauncherClassName(context));
            if (canResolveBroadcast(context, intent)) {
                context.sendBroadcast(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Default" + " Badge error", "set Badge failed");
        }

    }

    private static void setBadgeOfZUK(Context context, int count) {
        final Uri CONTENT_URI = Uri.parse("content://com.android.badge/badge");
        try {
            Bundle extra = new Bundle();
            extra.putInt("app_badge_count", count);
            context.getContentResolver().call(CONTENT_URI, "setAppBadgeCount", null, extra);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ZUK" + " Badge error", "set Badge failed");
        }
    }

    private static void setBadgeOfVIVO(Context context, int count) {
            try {
                Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
                intent.putExtra("packageName", context.getPackageName());

                intent.putExtra("className", getLauncherClassName(context));
                intent.putExtra("notificationNum", count);
                context.sendBroadcast(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    private static void setBadgeOfOPPO(Context context, int number) {
        try {
            Intent intent = new Intent("com.oppo.unsettledevent");
            intent.putExtra("pakeageName", context.getPackageName());
            intent.putExtra("number", number);
            intent.putExtra("upgradeNumber", number);
            if (canResolveBroadcast(context, intent)) {
                context.sendBroadcast(intent);
            } else {
                try {
                    Bundle extras = new Bundle();
                    extras.putInt("app_badge_count", number);
                    context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, extras);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setBadgeOfHuaWei(Context context, int count) {
        Bundle extra =new Bundle();
        extra.putString("package", context.getPackageName());
        extra.putString("class", getLauncherClassName(context));
        extra.putInt("badgenumber",count);
        context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, extra);
    }

    private static void setBadgeOfXIAOMI(Context context, final int count) {
        Log.d("xys", "Launcher : MIUI");
         final NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setContentTitle("title").setContentText("text");
        builder.setSmallIcon(R.mipmap.back);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.back));
        final Notification notification = builder.build();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                try {
                    Field field = notification.getClass().getDeclaredField("extraNotification");
                    Object extraNotification = field.get(notification);
                    Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
                    method.invoke(extraNotification, count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mNotificationManager.notify(0, notification);


            }
        }, 550);

    }




    /**
     * 设置索尼的Badge
     * <p/>
     * 需添加权限：<uses-permission android:name="com.sonyericsson.home.permission.BROADCAST_BADGE" />
     *
     * @param context context
     * @param count   count
     */
    private static void setBadgeOfSony(Context context, int count) {
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        boolean isShow = true;
        if (count == 0) {
            isShow = false;
        }
        Intent localIntent = new Intent();
        localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", isShow);//是否显示
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", launcherClassName);//启动页
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(count));//数字
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", context.getPackageName());//包名
        context.sendBroadcast(localIntent);
    }

    private static void setBadgeOfSumsung(Context context, int count) {
        // 获取你当前的应用
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);

    }

    private static void setBadgeOfHTC(Context context, int count) {
        Intent intentNotification = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
        ComponentName localComponentName = new ComponentName(context.getPackageName(), getLauncherClassName(context));
        intentNotification.putExtra("com.htc.launcher.extra.COMPONENT", localComponentName.flattenToShortString());
        intentNotification.putExtra("com.htc.launcher.extra.COUNT", count);
        context.sendBroadcast(intentNotification);

        Intent intentShortcut = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
        intentShortcut.putExtra("packagename", context.getPackageName());
        intentShortcut.putExtra("count", count);
        context.sendBroadcast(intentShortcut);
    }

    private static void setBadgeOfNova(Context context, int count) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tag", context.getPackageName() + "/" +
                getLauncherClassName(context));
        contentValues.put("count", count);
        context.getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"),
                contentValues);
    }

    public static void setBadgeOfMadMode(Context context, int count, String packageName, String className) {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", packageName);
        intent.putExtra("badge_count_class_name", className);
      //  context.sendBroadcast(intent);
        if (canResolveBroadcast(context, intent)) {
            context.sendBroadcast(intent);
        }
    }

    /**
     * 重置Badge
     *
     * @param context context
     */
    public static void resetBadgeCount(Context context, int iconResId) {
        setBadgeCount(context);
    }

    /**
     * return 启动器活动名称。从“android:名称”属性。
     * */
    private static String getLauncherClassName(Context context) {

        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        // To limit the components this Intent will resolve to, by setting an
        // explicit package name.
        intent.setPackage(context.getPackageName());
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        // All Application must have 1 Activity at least.
        // Launcher activity must be found!
        ResolveInfo info = packageManager
                .resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        // get a ResolveInfo containing ACTION_MAIN, CATEGORY_LAUNCHER
        // if there is no Activity which has filtered by CATEGORY_DEFAULT
        if (info == null) {
            info = packageManager.resolveActivity(intent, 0);
        }
        //////////////////////另一种实现方式//////////////////////
        // ComponentName componentName = context.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName()).getComponent();
        // return componentName.getClassName();
        //////////////////////另一种实现方式//////////////////////
        return info.activityInfo.name;

    }

    private static boolean canResolveBroadcast(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> receivers = packageManager.queryBroadcastReceivers(intent, 0);
        return receivers != null && receivers.size() > 0;
    }

}
