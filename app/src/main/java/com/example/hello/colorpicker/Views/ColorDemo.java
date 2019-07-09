package com.example.hello.colorpicker.Views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class ColorDemo extends View {

    private Paint paint;
    private TextPaint textPaint;

    private String rgb;

    private Rect colorDemoBounds;
    private Rect textBounds;




    public ColorDemo(Context context, AttributeSet attributeSet){

        super(context,attributeSet);

        colorDemoBounds = new Rect();
        textBounds = new Rect();
        paint = new Paint();
        setUpTextPaint();
        setColor(Color.BLUE);
    }

    private void setUpTextPaint(){

        textPaint = new TextPaint();
        textPaint.setColor(Color.RED);
        textPaint.setFlags(TextPaint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(10*getResources().getDisplayMetrics().scaledDensity);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       drawColorDemo(canvas);

       drawText(canvas);


    }

    public void setColor(int color){

        paint.setColor(color);

        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        rgb = "("+r+","+g+","+b+")";

        invalidate();

    }



    private void drawColorDemo(Canvas canvas){

        int padding = Math.round(getMeasuredWidth()/1.25f);

        colorDemoBounds.set(padding,0,getMeasuredWidth()-padding,getMeasuredHeight()/2);

        canvas.drawRect(colorDemoBounds,paint);

    }

    private void drawText(Canvas canvas){

        textPaint.getTextBounds(rgb,0,rgb.length(),textBounds);

        int remainingSpace = getMeasuredHeight() / 2;

        int textY = Math.round(getMeasuredHeight()/2 + remainingSpace /2.35f);

        canvas.drawText(rgb,getMeasuredWidth()/2-textBounds.width()/2,textY,textPaint);

    }







}
