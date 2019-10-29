package com.example.et.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.ImageLoaderUtil;
import com.example.et.Ustlis.LuBanManager.CompressInterface;
import com.example.et.Ustlis.LuBanManager.LuBanUtil;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.entnty.IntentCodeConfig;
import com.example.et.util.CacheUtils;
import com.example.et.util.CameraUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.constant.KeyValueConstants;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.model.CameraInterface;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人资料
 */
public class PersonalDataActivity extends BaseActivity implements CompressInterface.CompressListener, CameraInterface {

    @BindView(R.id.public_back)
    TextView publicBack;
    @BindView(R.id.public_title_tv)
    TextView publicTitleTv;
    @BindView(R.id.public_other)
    TextView publicOther;
    @BindView(R.id.iv_public_other)
    ImageView ivPublicOther;
    @BindView(R.id.rl_bacground)
    RelativeLayout rlBacground;
    @BindView(R.id.iv_holder)
    ImageView ivHolder;
    @BindView(R.id.Rl_hoder)
    RelativeLayout RlHoder;
    private Context context;
    /**
     * 选择设置图片方式
     */
    Uri imageUri;
    private String photoTypa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_personal_data);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        publicTitleTv.setText(getString(R.string.personal_data));
        //头像
        ImageLoaderUtil.loadCircleImage(context, CacheUtils.getInstance().getString(CacheConstants.photo_url)
                + CacheUtils.getInstance().getString(CacheConstants.photo), ivHolder);
    }

    @OnClick({R.id.public_back, R.id.Rl_hoder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_back:
                finish();
                break;
            case R.id.Rl_hoder:
                photoTypa = "user_head";
                // PhtotoPop(context);
                CameraUtils.show(this, this, this);
                break;
        }
    }

    @Override
    public void onImageUriSuccess(Uri uri) {
        this.imageUri = uri;
    }

    File a1;

    @Override
    public void onCompressSuccess(File images) {
        if ("user_head".equals(photoTypa)) {
            a1 = images;
            ivHolder.setImageBitmap(BitmapFactory.decodeFile(images.getPath()));
            upLoadUserInfo();
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CameraUtils.REQUEST_STORAGE_READ_ACCESS_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    int READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (READ_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED) {
                        CameraUtils.openPhotoAlbum(this);
                    } else {
                        CameraUtils.photoAlbum(this, this);
                    }
                } else {
                    ToastUtils.showShort(R.string.permissions_prohibited);
                }
                break;
            case CameraUtils.REQUEST_STORAGE_WRITE_ACCESS_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // takePhoto();


                    int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                    int CAMERA = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

                    if (WRITE_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED && CAMERA == PackageManager.PERMISSION_GRANTED) {
                        CameraUtils.openCamera(this, new File(imageUri.getPath()));
                    } else {
                        CameraUtils.camera(this, this);
                    }


                } else {
                    ToastUtils.showShort(R.string.permissions_prohibited);
                }
                break;
            default:

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IntentCodeConfig.enterMobileAlbum:    // 相册
                if (resultCode != RESULT_CANCELED) {
                    new LuBanUtil(context, this).getImageCompressPath(CameraUtils.getImgePath(this, data));
                }
                break;
            case IntentCodeConfig.enterMobileCamera: // 相机
                if (resultCode != RESULT_CANCELED && imageUri != null) {

                    new LuBanUtil(context, this).getImageCompressPath(imageUri.getPath());
                }
                break;
        }
    }

    /**
     * 实名认证上传用户信息
     */
    private void upLoadUserInfo() {

        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));

            jsonObject.put("photo", a1);//


            TaskPresenterUntils.lifeful(Constant.upphoto, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    if (success != null) {
                        Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, true)
                                .getStringMap();
                        LogUtils.i("================" + success + "=============" + resultMap.get(KeyValueConstants.MSG));
                        if (resultMap.get(KeyValueConstants.CODE).equals("200")) {
                            ActivityUtils.finishActivity(context);
                        }

                        ToastUtils.showShort(resultMap.get(KeyValueConstants.MSG).toString());
                    }
                }
            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
