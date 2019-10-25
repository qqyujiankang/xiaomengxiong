package com.example.et.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

public class MyViewFlipper extends ViewFlipper {
    public MyViewFlipper(Context context) {
        this(context, null);
    }

    public MyViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setDisplayedChild(int whichChild) {
        super.setDisplayedChild(whichChild);
        if (viewFlipperChangListener != null) {
            viewFlipperChangListener.setOnChangeListener((ViewGroup) getCurrentView());
        }

    }


    public void setOnChangeListener(ViewFlipperChangListener viewFlipperChangListener) {
        this.viewFlipperChangListener = viewFlipperChangListener;
    }

    private ViewFlipperChangListener viewFlipperChangListener;

    public abstract static class ViewFlipperChangListener {
        public abstract void setOnChangeListener(ViewGroup viewGroup);
    }


    void showOnly(int childIndex, boolean animate) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

        }
    }
}
