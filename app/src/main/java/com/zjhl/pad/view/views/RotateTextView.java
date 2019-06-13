package com.zjhl.pad.view.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.zjhl.pad.view.R;


/**
 * @desc: RotateTextView
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.views
 * @author: pluto
 * @create: 2018/5/14 15:24
 * @projectname: nnkj
 **/
@SuppressLint("AppCompatCustomView")
public class RotateTextView extends TextView {


//    public RotateTextView(Context context) {
//        super(context);
//    }
//
//    public RotateTextView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        //倾斜度45,上下左右居中
//        canvas.rotate(45, getMeasuredWidth()/2, getMeasuredHeight()/2);
//        super.onDraw(canvas);
//    }
private static final int DEFAULT_DEGREES = 0;
    private int mDegrees;

    public RotateTextView(Context context) {
        super(context, null);
    }

    public RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.textViewStyle);
        this.setGravity(Gravity.CENTER);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RotateTextView);
        mDegrees = a.getDimensionPixelSize(R.styleable.RotateTextView_degree,
                DEFAULT_DEGREES);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        canvas.rotate(mDegrees, this.getWidth() / 2f, this.getHeight() / 2f);
        super.onDraw(canvas);
        canvas.restore();
    }

    public void setDegrees(int degrees) {
        mDegrees = degrees;
    }
}
