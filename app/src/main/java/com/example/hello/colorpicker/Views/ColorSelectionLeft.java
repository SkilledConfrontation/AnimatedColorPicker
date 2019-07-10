package com.example.hello.colorpicker.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.hello.colorpicker.R;

public class ColorSelectionLeft extends View  {

    private Bitmap cursor;
    private Bitmap color;

    private int cursorY;

    private ColorSelectionSquare colorSelectionSquare;


    private Rect bounds;

    private onColorSlided onColorSlided;


    public ColorSelectionLeft(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        bounds = new Rect();
    }


    public void setColorSelectionSquare(ColorSelectionSquare colorSelectionSquare){

        this.colorSelectionSquare = colorSelectionSquare;

    }

    public void setOnColorSlided(onColorSlided onColorSlided){

        this.onColorSlided = onColorSlided;


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        canvas.clipRect(bounds);

        canvas.drawBitmap(color,0,0,null);

        canvas.restore();

        canvas.drawBitmap(cursor,0,cursorY,null);


    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();


        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {

            cursorY = (int) event.getY();

            checkTopBounds();
            checkBottomBounds();

            if(onColorSlided != null){

                onColorSlided.onColorSlide(color.getPixel(0,getMiddleY()));


            }


            invalidate();

            return true;

        }

        return false;

    }


    private int getMiddleY(){

        return cursorY + cursor.getHeight()/2;

    }

    private void setCursorY(int startLocation){

        cursorY = startLocation-cursor.getHeight()/2;

    }

    private void checkTopBounds(){

        boolean outOfTopBounds = getMiddleY() < bounds.top;

        if(outOfTopBounds){

            setCursorY(bounds.top);

        }

    }

    private void checkBottomBounds(){

        boolean outOfBottomBounds = getMiddleY() > bounds.bottom;

        if(outOfBottomBounds){

            setCursorY(bounds.bottom);

        }

    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cursor = BitmapFactory.decodeResource(getResources(),R.drawable.cursor);
        createColorBitmap();
        setUpColorBounds();
    }



    private void createColorBitmap(){

          Bitmap original = BitmapFactory.decodeResource(getResources(),R.drawable.color_left_selection);

          color = Bitmap.createScaledBitmap(original,getMeasuredWidth(),getMeasuredHeight(),false);


    }

    private void setUpColorBounds(){

        Bitmap target = BitmapFactory.decodeResource(getResources(),R.drawable.target);

        bounds = new Rect();

        int start = target.getHeight()/2;

        bounds.set(cursor.getWidth(),start,getMeasuredWidth()-cursor.getWidth(),getMeasuredHeight()-start);


    }


    public interface onColorSlided{

        void onColorSlide(int pixelColor);

    }


}