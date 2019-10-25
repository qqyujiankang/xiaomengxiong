package com.example.et.util.view;

/**
 * Created by ywx on 2018/5/6.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by 折扣卷布局 on 2016/5/17.
 */
public class CouponsView extends LinearLayout {

    private Paint mPaint;
    /**
     * 圆间距
     */
    private float gap = 8;
    /**
     * 半径
     */
    private float radius = 10;
    /**
     * 圆数量
     */
    private int circleNum;

    private float remain;
    private int color = Color.WHITE;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public CouponsView(Context context) {
        super(context);
    }

    public CouponsView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {
            remain = (int) (w - gap) % (2 * radius + gap);
        }
        circleNum = (int) ((w - gap) / (2 * radius + gap));
    }


    public CouponsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(getColor());
        mPaint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < circleNum; i++) {
            float x = gap + radius + remain / 2 + ((gap + radius * 2) * i);
            //  canvas.drawCircle(x,0,radius,mPaint);
            canvas.drawCircle(x, getHeight(), radius, mPaint);
        }
    }

}