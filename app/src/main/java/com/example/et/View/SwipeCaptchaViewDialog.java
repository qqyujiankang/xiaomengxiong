package com.example.et.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.et.Activity.AppointmentcontractActivity;
import com.example.et.Activity.MainActivity;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.Ustlis.ActivityUtils;
import com.example.et.Ustlis.ScreenUtils;
import com.example.et.Ustlis.ToastUtils;
import com.example.et.fragment.HeadFragment;
import com.example.et.util.CacheUtils;
import com.example.et.util.ImageUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.constant.KeyValueConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.et.Ustlis.ActivityUtils.startActivity;


public class SwipeCaptchaViewDialog extends Dialog implements SwipeCaptchaView.OnCaptchaMatchCallback, SeekBar.OnSeekBarChangeListener {
    SwipeCaptchaView mSwipeCaptchaView;
    SeekBar mSeekBar;
    private Context context;
    private List<String> list1 = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private int id;
    private Lifeful lifeful;

    public SwipeCaptchaViewDialog(@NonNull Context context, int id, Lifeful lifeful) {

        super(context);
        this.context = context;
        this.id = id;
        this.lifeful = lifeful;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_swipe_captcha_view);
        mSwipeCaptchaView = (SwipeCaptchaView) findViewById(R.id.swipeCaptchaView);
        mSeekBar = (SeekBar) findViewById(R.id.dragBar);
        //测试从网络加载图片是否ok
        list.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3822265507,2412038066&fm=26&gp=0.jpg");
       // list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574260073716&di=ab496127ddd65452e46d10cd55fce771&imgtype=0&src=http%3A%2F%2Fimg2.zol.com.cn%2Fproduct%2F89%2F111%2FceZXuwwDtXoUE.jpg");
        list1 = createRandomList(list, 1);
        Glide.with(context).load(list1.get(0))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        LogUtils.i("exp=====" + resource + "================" + transition);
                        if (!resource.equals("")) {
                            mSwipeCaptchaView.setImageBitmap(ImageUtils.drawable2Bitmap(resource));
                            //mSwipeCaptchaView.createCaptcha();
                        }

                    }

                });
        mSeekBar.setOnSeekBarChangeListener(this);
        mSwipeCaptchaView.setOnCaptchaMatchCallback(this);


    }

    @Override
    public void matchSuccess(SwipeCaptchaView swipeCaptchaView) {
     //   Toast.makeText(context, R.string.tes78, Toast.LENGTH_SHORT).show();
        //swipeCaptcha.createCaptcha();
        redata(id);
        mSeekBar.setEnabled(false);
    }

    @Override
    public void matchFailed(SwipeCaptchaView swipeCaptchaView) {
        //Toast.makeText(context, R.string.tet31, Toast.LENGTH_SHORT).show();
        swipeCaptchaView.resetCaptcha();
        mSeekBar.setProgress(0);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mSwipeCaptchaView.setCurrentSwipeValue(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //随便放这里是因为控件
        mSeekBar.setMax(mSwipeCaptchaView.getMaxSwipeValue());
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Log.d("zxt", "onStopTrackingTouch() called with: seekBar = [" + seekBar + "]");
        mSwipeCaptchaView.matchCaptcha();
    }

    private void redata(int i) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("cid", id);


            TaskPresenterUntils.lifeful(Constant.tocontract, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    LogUtils.i("=======lifeful====" + success);
                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();
                    if (resultMap.get(KeyValueConstants.CODE).equals("200")) {

                        Intent i = new Intent();
                        i.setClass(getContext(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtra("fragment_flag", 2);
                        startActivity(i);
                        HeadFragment.state="0";
                        ActivityUtils.finishActivity(AppointmentcontractActivity.class);
                        dismiss();


                    }

                    if (!resultMap.get(KeyValueConstants.MSG).equals("")) {

                        dismiss();
                        ToastUtils.showShort(resultMap.get(KeyValueConstants.MSG).toString());

                    }


                }

            }, lifeful));
        } catch (
                JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 从list中随机抽取元素
     *
     * @param list
     * @param n
     * @return void
     * @throws
     * @Title: createRandomList
     * @Description: TODO
     */
    private static List createRandomList(List list, int n) {
        // TODO Auto-generated method stub
        Map map = new HashMap();
        List listNew = new ArrayList();
        if (list.size() <= n) {
            return list;
        } else {
            while (map.size() < n) {
                int random = (int) (Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");

                    listNew.add(list.get(random));
                }
            }
            return listNew;
        }
    }

}
