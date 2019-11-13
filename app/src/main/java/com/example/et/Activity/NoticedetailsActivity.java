package com.example.et.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.et.Constant;
import com.example.et.R;
import com.example.et.util.CacheUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlFormatter;
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder;
import org.sufficientlysecure.htmltextview.HtmlResImageGetter;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 公告详情
 */
public class NoticedetailsActivity extends BaseActivity {


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
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    private int nid;
    private Context context;
    private Lifeful lifeful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        lifeful = this;
        setContentView(R.layout.activity_noticedetails);
        ButterKnife.bind(this);
        getIntentDatas();
        initView();
        requestDatas();

    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>html{padding:15px;} body{word-wrap:break-word;font-size:13px;padding:0px;margin:0px} " +
                "p{padding:0px;margin:0px;font-size:13px;color:#E5EAFF;line-height:1.3;} img{padding:0px,margin:0px;max-width:100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    @Override
    public void requestDatas() {
        super.requestDatas();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", CacheUtils.getInstance().getString(CacheConstants.PHONE));
            jsonObject.put("nid", nid);

            TaskPresenterUntils.lifeful(Constant.newsone, jsonObject, new OnLoadLifefulListener<String>(null, new OnLoadListener<String>() {
                @Override
                public void onSuccess(String success) {
                    Map<String, Object> resultMap = ParseUtils.analysisListTypeDatasAndCount((Activity) context, success, null, false).getStringMap();
                    tvName.setText(resultMap.get("name").toString());
                    tvTime.setText(resultMap.get("time").toString());
                   // tvContent.setText(Html.fromHtml(resultMap.get("text").toString()));
                   //loads html from string and displays cat_pic.png from the app 's drawable folder
                    Spanned formattedHtml = HtmlFormatter.formatHtml(new HtmlFormatterBuilder().setHtml(resultMap.get("text").toString()).setImageGetter(new HtmlResImageGetter(tvContent.getContext())));
                    tvContent.setText(formattedHtml);
                    //webView.loadData(getHtmlData(resultMap.get("text").toString()), "text/html;charset=utf-8", "utf-8");


                }

            }, this));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void getIntentDatas() {
        super.getIntentDatas();
        nid = getIntent().getIntExtra("nid", 0);
    }

    @Override
    public void initView() {
        super.initView();

        publicTitleTv.setText(R.string.message);
//        WebSettings settings = webView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setDomStorageEnabled(true);
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
//        webView.setWebViewClient(new MyWebViewClient(NoticedetailsActivity.this));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
//        } else {
//            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//        }
//        webView.setBackgroundColor(0); // 设置背景色
//        webView.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255


    }

    @OnClick(R.id.public_back)
    public void onViewClicked() {
        finish();
    }
}


