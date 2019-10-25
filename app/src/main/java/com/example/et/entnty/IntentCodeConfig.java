package com.example.et.entnty;

/**
 * Created by Administrator on 2018/4/18.
 */

public class IntentCodeConfig {
    public final static int resultCode=1000;
    public final static int requestCode_dissf=resultCode+1;






    public final static int modifyCode = resultCode+1000;
    /**  更换头像  **/
    public final static int enterMobileAlbum = modifyCode + 200;				// 跳转到手机相册
    public final static int enterMobileCamera = enterMobileAlbum + 1;	// 跳转到手机相机
    public final static int enterCopyImage = enterMobileCamera + 1;		// 跳转到手机自带截图
    public final static int enterCopyImage2 = enterCopyImage + 1;		// 跳转到手机自带截图
    public final static int SCAN_RESULT_CODE = enterCopyImage2+1;		// 扫一扫请求



}
