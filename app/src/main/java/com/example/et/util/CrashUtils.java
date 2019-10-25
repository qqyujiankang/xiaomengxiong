package com.example.et.util;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

import com.example.et.Ustlis.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * <pre>

 *     desc  : 崩溃相关工具类
 * </pre>
 */
public final class CrashUtils {

    private static String defaultDir;
    private static String dir;
    private static String versionName;
    private static int    versionCode;

    private static final String FILE_SEP = System.getProperty("file.separator");
    @SuppressLint("SimpleDateFormat")
    private static final Format FORMAT   = new SimpleDateFormat("MM-dd HH-mm-ss");

    private static final String CRASH_HEAD;

    private static final UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER;
    private static final UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER;

    private static OnCrashListener sOnCrashListener;

    static {
        try {
            PackageInfo pi = Utils.getApp()
                    .getPackageManager()
                    .getPackageInfo(Utils.getApp().getPackageName(), 0);
            if (pi != null) {
                versionName = pi.versionName;
                versionCode = pi.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        CRASH_HEAD = "************* Crash Log Head ****************" +
                "\nDevice Manufacturer: " + Build.MANUFACTURER +// 设备厂商
                "\nDevice Model       : " + Build.MODEL +// 设备型号
                "\nAndroid Version    : " + Build.VERSION.RELEASE +// 系统版本
                "\nAndroid SDK        : " + Build.VERSION.SDK_INT +// SDK 版本
                "\nApp VersionName    : " + versionName +
                "\nApp VersionCode    : " + versionCode +
                "\n************* Crash Log Head ****************\n\n";

        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();

        UNCAUGHT_EXCEPTION_HANDLER = new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                if (e == null) {
                    if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, null);
                    } else {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                    return;
                }

                StringBuilder sb = new StringBuilder();
                sb.append(CRASH_HEAD);
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                Throwable cause = e.getCause();
                while (cause != null) {
                    cause.printStackTrace(pw);
                    cause = cause.getCause();
                }
                pw.flush();
                sb.append(sw.toString());
                final String crashInfo = sb.toString();

                Date now = new Date(System.currentTimeMillis());
                String fileName = FORMAT.format(now) + ".txt";
                final String fullPath = (dir == null ? defaultDir : dir) + fileName;
                if (createOrExistsFile(fullPath)) {
                    input2File(crashInfo, fullPath);
                } else {
                    Log.e("CrashUtils", "create " + fullPath + " failed!");
                }

                if (sOnCrashListener != null) {
                    sOnCrashListener.onCrash(crashInfo, e);
                }

                if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                    DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, e);
                }
            }
        };
    }

    private CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     */
    public static void init() {
        init("");
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDir 崩溃文件存储目录
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(@NonNull final File crashDir) {
        init(crashDir.getAbsolutePath(), null);
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDirPath 崩溃文件存储目录
     */
    public static void init(final String crashDirPath) {
        init(crashDirPath, null);
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * （异常文件路径默认值） storage/emulated/0/Android/data/com.example.administrator.huijianzhi/cache/crash/
     * @param onCrashListener 崩溃监听事件
     */
    public static void init(final OnCrashListener onCrashListener) {
        init("", onCrashListener);
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDir        崩溃文件存储目录
     * @param onCrashListener 崩溃监听事件
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(@NonNull final File crashDir, final OnCrashListener onCrashListener) {
        init(crashDir.getAbsolutePath(), onCrashListener);
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDirPath        崩溃文件存储目录
     * @param onCrashListener 崩溃监听事件
     */
    public static void init(final String crashDirPath, final OnCrashListener onCrashListener) {
        if (isSpace(crashDirPath)) {
            dir = null;
        } else {
            dir = crashDirPath.endsWith(FILE_SEP) ? crashDirPath : crashDirPath + FILE_SEP;
        }
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && Utils.getApp().getExternalCacheDir() != null)
            defaultDir = Utils.getApp().getExternalCacheDir() + FILE_SEP + "crash" + FILE_SEP; //（异常文件路径默认值） storage/emulated/0/Android/data/com.example.administrator.huijianzhi/cache/crash/
        else {
            defaultDir = Utils.getApp().getCacheDir() + FILE_SEP + "crash" + FILE_SEP;
        }
        sOnCrashListener = onCrashListener;
        Thread.setDefaultUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
    }

    private static void input2File(final String input, final String filePath) {
        Future<Boolean> submit = Executors.newSingleThreadExecutor().submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter(filePath, true));
                    bw.write(input);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
                        if (bw != null) {
                            bw.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        try {
            if (submit.get()) return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.e("CrashUtils", "write crash info to " + filePath + " failed!");
    }

    private static boolean createOrExistsFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public interface OnCrashListener {
        void onCrash(String crashInfo, Throwable e);
    }
}
