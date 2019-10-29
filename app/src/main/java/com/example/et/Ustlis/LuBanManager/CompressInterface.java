package com.example.et.Ustlis.LuBanManager;


import java.io.File;

/**
 */

public interface CompressInterface {
    void compress();

    /**
     * 压缩结果监听器
     */
    interface CompressListener {
        /**
         * 压缩成功
         * @param images 已经压缩图片
         */
        void onCompressSuccess(File images);

        /**
         * 压缩失败
         * @param images 压缩失败的图片
         * @param msg 失败的原因
         */
      //  void onCompressError(String images, String msg);
    }
}
