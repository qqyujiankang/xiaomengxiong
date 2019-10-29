package com.example.et.Ustlis.LuBanManager;

import android.content.Context;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * Created by Administrator on 2017/6/5.
 */

public class LuBanUtil {
    private Context mContext;
    private String imageCompressPath = null;
    private CompressInterface.CompressListener listener;

    public LuBanUtil(Context mContext, CompressInterface.CompressListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    public void getImageCompressPath(final String imagePath) {
        //
        if (imagePath == null) {
            return;
        } else {

            Luban.get(mContext)
                    .load(new File(imagePath))                     //传人要压缩的图片
                    .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                    .setCompressListener(new OnCompressListener() { //设置回调
                        @Override
                        public void onStart() {
                            //TODO 压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            //TODO 压缩成功后调用，返回压缩后的图片文件


                            // File a=  new File(file.getParent() + File.separator + "IDCardPositiveImg.jpg");
                            //  imageCompressPath = a.getPath();
                            if (file.length() > 0) {
                                listener.onCompressSuccess(file);
                            }


                        }

                        @Override
                        public void onError(Throwable e) {
                            //  OutputUtils.showToast(mContext,mContext.getString(R.string.compress_fail));

                        }
                    }).launch();    //启动压缩
        }
    }
}
