
package com.example.et.util;

import android.Manifest;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import com.example.et.R;
import com.example.et.View.PubliccAlertDialog;
import com.example.et.entnty.IntentCodeConfig;
import com.example.et.util.model.CameraInterface;

import java.io.File;

/**
 * Created by ywx on 2018/3/28.
 * <p>
 * 相机相册
 */

public class CameraUtils {
    public static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    public static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    private CameraUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void show(final ContextWrapper wrapper, final Activity activity, final CameraInterface cameraModel) {
        PubliccAlertDialog publiccAlertDialog = new PubliccAlertDialog(activity) {
            @Override
            public void clickCallBack() {

                cameraModel.onImageUriSuccess(Uri.fromFile(CameraUtils.photoAlbum(wrapper, activity)));
                //  photoAlbum();
            }

            @Override
            public void clickBack() {
                cameraModel.onImageUriSuccess(Uri.fromFile(CameraUtils.camera(wrapper, activity)));


                // takePhoto();
            }
        };
        publiccAlertDialog.show();

    }


    /**
     * 照相机
     */
    public static File camera(ContextWrapper wrapper, Activity activity) {
        File file = createFile();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (wrapper.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && wrapper.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                openCamera(activity, file);
            } else {
                if (wrapper.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    requestPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, activity.getString(R.string.permission_write_storage_rationale), REQUEST_STORAGE_WRITE_ACCESS_PERMISSION);
                }
                if (wrapper.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    requestPermission(activity, Manifest.permission.CAMERA, activity.getString(R.string.permission_write_storage_rationale1), REQUEST_STORAGE_WRITE_ACCESS_PERMISSION);
                }

            }
        } else {
            openCamera(activity, file);
        }
        return file;
    }

    public static void openCamera(Activity activity, File file) {

        Uri imageUri = Uri.fromFile(file);
        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //下面这句指定调用相机拍照后的照片存储的路径
        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(takeIntent, IntentCodeConfig.enterMobileCamera);
    }

    private static File createFile() {
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        if (FileUtils.isFileExists(file)) {
            FileUtils.deleteFile(file);
        } else {
            FileUtils.createOrExistsFile(file);
        }
        return file;
    }

    protected static void requestPermission(Activity activity, final String permission, String rationale, final int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.shouldShowRequestPermissionRationale(permission)) {
                activity.requestPermissions(new String[]{permission}, requestCode);
            } else {
                activity.requestPermissions(new String[]{permission}, requestCode);

            }
        }
    }


    /**
     * 相册取图片
     */
    public static File photoAlbum(ContextWrapper wrapper, Activity activity) {
        File file = createFile();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (wrapper.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE,
                        activity.getString(R.string.permission_read_storage_rationale),
                        REQUEST_STORAGE_READ_ACCESS_PERMISSION);
            } else {
                openPhotoAlbum(activity );
            }
        } else {
            openPhotoAlbum(activity);
        }
        return file;
    }

    public static void openPhotoAlbum(Activity activity) {
      /*  Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image*//*");
        activity.startActivityForResult(pickIntent, IntentCodeConfig.enterCopyImage2);
*/
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, IntentCodeConfig.enterMobileAlbum);

    }

    public static String getImgePath(Activity activity, Intent intent) {
        Uri uri = intent.getData();
        String[] proj = { MediaStore.Images.Media.DATA };

        Cursor actualimagecursor = activity.managedQuery(uri,proj,null,null,null);

        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        return img_path;
    }

    public static final String CAMERA = "camera";//相机裁剪
    public static final String ALBUM = "album";//相册裁剪
/*
    public static void cropTakePhoto(Activity activity, String cropType, final Uri uri, Uri cropUri, Intent data) {
        if (cropType.equals(CAMERA)) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            //   就是intent.setDataAndType(uri, "image*//*");和intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);中uri的地址必须是不同的。
            intent.setDataAndType(uri, "image*//*");
            intent.putExtra("crop", "true");
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", true);
            activity.startActivityForResult(intent, IntentCodeConfig.enterMobileCamera);

        } else if (cropType.equals(ALBUM)) {
            if (data != null && data.getData() != null) {
                // 可以直接显示图片,或者进行其他处理(如压缩或裁剪等)
                // 对图片进行裁剪
                Intent intent1 = new Intent("com.android.camera.action.CROP");
                intent1.setDataAndType(data.getData(), "image*//*");
                intent1.putExtra("crop", "true");
                intent1.putExtra("scale", true);
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                intent1.putExtra("return-data", false);
                intent1.putExtra("noFaceDetection", true);
                activity.startActivityForResult(intent1, IntentCodeConfig.enterMobileAlbum);
            }

        }


    }*/

    // CameraUtil的方法
    public static final String FILE_PATH = Environment.getExternalStorageDirectory().toString() + "/yiweixun/";

/*    public static Uri getTempUri() {
        String fileName = System.currentTimeMillis() + ".jpg";
        File out = new File(FILE_PATH);
        if (!out.exists()) {
            out.mkdirs();
        }
        out = new File(FILE_PATH, fileName);
        return Uri.fromFile(out);
    }*/
/**
* 从intent中获取图片的路径
* */



}
