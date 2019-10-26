package com.example.et.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.et.Adapter.AnswerAdapter;
import com.example.et.Adapter.TestCheckOneAdapter;
import com.example.et.Constant;
import com.example.et.R;
import com.example.et.entnty.Answer;
import com.example.et.util.CacheUtils;
import com.example.et.util.LogUtils;
import com.example.et.util.TaskPresenterUntils;
import com.example.et.util.constant.CacheConstants;
import com.example.et.util.constant.KeyValueConstants;
import com.example.et.util.lifeful.Lifeful;
import com.example.et.util.lifeful.OnLoadLifefulListener;
import com.example.et.util.lifeful.OnLoadListener;
import com.example.et.util.realize.ParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnswerDiog extends AlertDialog {
    private View.OnClickListener onClickListener;
    private AdapterView.OnItemClickListener onItemClickListener;
    private Lifeful Lifeful;
    private Activity context;
    private String problem;
    private List<Answer> answers;

    public AnswerDiog(Activity context, View.OnClickListener onClickListener, AdapterView.OnItemClickListener onItemClickListener, String problem, List<Answer> answers) {
        super(context);
        this.onClickListener = onClickListener;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.problem = problem;
        this.answers = answers;
    }

    private ListView listv;
    private Button btnconfirm;
    private TextView tvissue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window_answer);
        listv = findViewById(R.id.listv);
        btnconfirm = findViewById(R.id.btn_confirm);
        tvissue = findViewById(R.id.tv_issue);
        btnconfirm.setOnClickListener(onClickListener);
        listv.setOnItemClickListener(onItemClickListener);
        listv.setAdapter(new TestCheckOneAdapter(context, answers));
        tvissue.setText(problem);

    }


}
