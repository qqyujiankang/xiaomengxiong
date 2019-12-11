package com.example.et.Ustlis;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.et.R;


/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class ImageView extends AppCompatImageView {

    private Context context;
    private String image_name;
    private int rounding_radius;

    public ImageView(Context context) {
        super(context); // TODO Auto-generated constructor stub
    }

    public ImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
      //  TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageViewUrl);
      //  image_name = a.getString(R.styleable.ImageViewUrl_image_name);
      //  rounding_radius = a.getInt(R.styleable.ImageViewUrl_rounding_radius, 1);
        // setImage(context, JniUrl.getHttpUrl(0) + image_name + ".png", this, rounding_radius);
       // a.recycle();
    }

      /*upload\/head\/1526001476894.jpg
    * // 加载本地图片
File file = new File(getExternalCacheDir() + "/image.jpg");
Glide.with(this).load(file).into(imageView);

// 加载应用资源
int resource = R.drawable.image;
Glide.with(this).load(resource).into(imageView);

// 加载二进制流
byte[] image = getImageBytes();
Glide.with(this).load(image).into(imageView);

// 加载Uri对象
Uri imageUri = getImageUri();
Glide.with(this).load(imageUri).into(imageView);
    *
    *
    *
    * */

    public void setImage(Context context, String imageUrl, AppCompatImageView imageView, int roundingRadius) {
        if (context != null && imageUrl != null && !imageUrl.equals("") && imageView != null && roundingRadius > 0) {
            /*
            *
            * Glide的GitHub主页的地址是：https://github.com/bumptech/glide

不过在这个地址下载到的永远都是最新的源码，有可能还正在处于开发当中。而我们整个系列都是使用Glide 3.7.0这个版本来进行讲解的，因此如果你需要专门去下载3.7.0版本的源码，可以到这个地址进行下载：https://github.com/bumptech/glide/tree/v3.7.0

            Glide 以 url、viewwidth、viewheight、屏幕的分辨率等做为联合key，官方api中没有提供删除图片缓存的函数，官方提供了signature()方法，将一个附加的数据加入到缓存key当中，多媒体存储数据，可用MediaStoreSignature类作为标识符，
            会将文件的修改时间、mimeType等信息作为cacheKey的一部分，通过改变key来实现图片失效相当于软删除。

            Glide.with(this).load(imageUrl).skipMemoryCache(true).into(imageView);
设置磁盘缓存策略

Glide.with(this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

all:缓存源资源和转换后的资源
none:不作任何磁盘缓存
source:缓存源资源
result：缓存转换后的资源

Glide.get(this).clearDiskCache();//清理磁盘缓存需要在子线程中执行
 Glide.get(this).clearMemory();//清理内存缓存 可以在UI主线程中进行

            * */

            LruBitmapPool pool = new LruBitmapPool((int) (Runtime.getRuntime().maxMemory()) / 8);
            //    DiskCacheStrategy.AUTOMATIC
            Glide.with(context)
                    .load(imageUrl)
                    .apply(new RequestOptions().transforms(new RoundedCorners(roundingRadius)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(imageView);
            //    Glide.with(context).load("file:///android_asset/app_load_bg.png").into(imageView);

        } else {

            throw new NullPointerException("context or imageUrl or imageView is null  or roundingRadius<=0");
        }
    }
    //加载本地资源
    public void setImage(Context context, @RawRes @DrawableRes @Nullable Integer resourceId, AppCompatImageView imageView, int roundingRadius) {
        if (context != null && imageView != null && roundingRadius > 0) {
            LruBitmapPool pool = new LruBitmapPool((int) (Runtime.getRuntime().maxMemory()) / 8);
            Glide.with(context)
                    .load(resourceId)
                    .apply(new RequestOptions().transforms(new RoundedCorners(roundingRadius)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(imageView);
        } else {
            throw new NullPointerException("context or imageUrl or imageView is null  or roundingRadius<=0");
        }
    }

    /**
     * 设置图片
     * 解析数据的图片                 http://app.futureaw.com/dbs/upload/head/1526001476894.jpg
     */
    public void setImageName(String image_name) {

        //   setImage(context, JniUrl.getHttpUrl(89) + image_name, this, rounding_radius);
        setImage(context, image_name, this, rounding_radius);

    }
    public void setImageName(@RawRes @DrawableRes @Nullable Integer resourceId) {
    setImage(context, resourceId, this, rounding_radius);
    }

    public void setImageName(String image_name, int rounding_radius) {
        setImage(context, image_name, this, rounding_radius);
    }


    public void setImageName(@RawRes @DrawableRes @Nullable Integer resourceId, int rounding_radius) {
        setImage(context, resourceId, this, rounding_radius);
    }
    /**
     * @param rounding_radius  >0
     * 设置控件宽度，
     * 动态加载图片,并设置控件高度
     *
     * @
     * */
    public void setImageNameMeasureWH(String image_name, int rounding_radius, int width) {
        if (rounding_radius<=0){
            rounding_radius=1;
        }
        setImage(context, image_name, rounding_radius, width);//http://192.168.1.254/dbs/upload/activity/ededd01cd1684f15acca47b41e880ec5.png

    }

    private void setImage(Context context, String image_name, int roundingRadius, final int width) {
        Glide.with(context)
                .load(image_name)
                .apply(new RequestOptions().transforms(new RoundedCorners(roundingRadius)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(new CustomViewTarget<View, Drawable>(this) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition transition) {
                        double resourceWidth = resource.getMinimumWidth();
                        double resourceHeight = resource.getMinimumHeight();
                        ViewGroup.LayoutParams layoutParams = getLayoutParams();
                        layoutParams.width = width;
                        double ratio=resourceWidth / resourceHeight;
                        double height=((double)width )/ratio;
                        layoutParams.height = (int) height;
                        setLayoutParams(layoutParams);
                        setImageDrawable(resource);

                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {
                        //  LogUtils.i("exp_tag", "onResourceCleared");
                    }
                });

    }

//    /**
//     * 设置图片
//     * 圆形
//     * 解析数据的图片
//     */
//    public void setCircleCrop(String image_name) {
//        Glide.with(context).load(image_name).apply(RequestOptions.bitmapTransform(new CircleCrop()).error(R.mipmap.user_icon_def).placeholder(R.mipmap.user_icon_def)).into(this);
//    }
//    public void setCircleCrop2(Bitmap image_name) {
//        Glide.with(context).load(image_name).apply(RequestOptions.bitmapTransform(new CircleCrop()).error(R.mipmap.user_icon_def).placeholder(R.mipmap.user_icon_def)).into(this);
//    }

    /**
     * 设置图片
     * 圆形
     * 解析数据的图片
     */
    public void setCircleCrop1(String image_name) {
        //   setCircleCrop(context, image_name, this, rounding_radius);

    }

    /**
   *
     */
   /* private void setCircleCrop(Context context, String imageUrl, ImageView publicImageView, int rounding_radius) {
        Glide.with(context).load(imageUrl).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(publicImageView);


    }*/

    private static final class TestViewTarget extends CustomViewTarget<View, Object> {

        TestViewTarget(View view) {
            super(view);
        }

        @Override
        protected void onResourceCleared(@Nullable Drawable placeholder) {
            // Intentionally Empty.
        }

        // We're intentionally avoiding the super call.
        @SuppressWarnings("MissingSuperCall")
        @Override
        public void onResourceReady(
                @NonNull Object resource, @Nullable Transition<? super Object> transition) {
            // Avoid calling super.
        }

        // We're intentionally avoiding the super call.
        @SuppressWarnings("MissingSuperCall")
        @Override
        public void onResourceLoading(@Nullable Drawable placeholder) {
            // Avoid calling super.
        }

        // We're intentionally avoiding the super call.
        @SuppressWarnings("MissingSuperCall")
        @Override
        public void onLoadFailed(@Nullable Drawable errorDrawable) {
            // Avoid calling super.
        }
    }


}
