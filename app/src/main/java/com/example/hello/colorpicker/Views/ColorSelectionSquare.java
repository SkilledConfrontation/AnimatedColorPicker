package com.example.hello.colorpicker.Views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.hello.colorpicker.R;


public class ColorSelectionSquare extends View {

    private Paint paint;

    private Bitmap target;

    private Bitmap colorBitmap;
    private Canvas colorCanvas;

    private int targetX;
    private int targetY;

    private int color = Color.BLUE;

    private LinearGradient blackAndWhite;
    private LinearGradient colorShader;
    private ComposeShader composeShader;

    private Rect colorBounds;

    private onColorChoosen onColorChoosen;



    public ColorSelectionSquare(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        paint = new Paint();
        target = BitmapFactory.decodeResource(getResources(),R.drawable.target);
        colorBounds = new Rect();

    }



    @Override
    protected void onDraw(Canvas canvas) {

        paint.setShader(composeShader);

        colorCanvas.drawRect(colorBounds,paint);


        canvas.drawBitmap(colorBitmap,0,0,null);


        canvas.drawBitmap(target,targetX,targetY,null);


    }

    public void setOnColorChoosen(onColorChoosen onColorChoosen){

        this.onColorChoosen = onColorChoosen;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       Log.i("Event",String.valueOf(event.getAction()));


        if(event.getActionMasked() == MotionEvent.ACTION_DOWN || event.getActionMasked() == MotionEvent.ACTION_MOVE){


            targetX = (int) event.getX();
            targetY = (int) event.getY();

            getXCoordinates();
            getYCoordinates();


          int color = colorBitmap.getPixel(getMiddleX(),getMiddleY());

          if(onColorChoosen!=null){

              onColorChoosen.onColorChoosen(color);

          }


            invalidate();



            return true;

        }

        return true;

    }




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        colorBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);

        colorCanvas = new Canvas(colorBitmap);

        blackAndWhite = new LinearGradient(0,0,0,getMeasuredHeight(),Color.WHITE,Color.BLACK,Shader.TileMode.CLAMP);


        setColorShader();

        colorBounds.set(target.getWidth(),target.getHeight()/2,colorBitmap.getWidth()-target.getWidth(),colorBitmap.getHeight()-(target.getHeight()/2));

        targetX = colorBounds.left;
        targetY = colorBounds.top;

    }

    public void setPaint(int color){

        this.color = color;
        setColorShader();
        invalidate();

    }



    public int getCurrentColor(){

        return colorBitmap.getPixel(getMiddleX(),getMiddleY());

    }



    private void setColorShader(){

        colorShader = new LinearGradient(0,0,getMeasuredWidth(),0,Color.WHITE,color,Shader.TileMode.CLAMP);
        composeShader = new ComposeShader(blackAndWhite,colorShader,PorterDuff.Mode.MULTIPLY);

        paint.setShader(composeShader);

        colorCanvas.drawRect(colorBounds,paint);

    }


    private int getMiddleX(){

        return targetX + target.getWidth()/2;

    }

    private int getMiddleY(){

        return targetY + target.getHeight()/2;

    }


    private boolean isXInBounds(){

        return getMiddleX() > colorBounds.left && getMiddleX() < colorBounds.right;

    }

    private boolean isYInBounds(){

       return getMiddleY() > colorBounds.top && getMiddleY() < colorBounds.bottom;

    }

    private void getXCoordinates(){

        if(!isXInBounds()&&getMiddleX() >colorBounds.width()/2){

            targetX = (colorBounds.right-target.getWidth()/2)-1;

        }else if(!isXInBounds()&&getMiddleX() < colorBounds.width()/2){

            targetX = colorBounds.left-target.getWidth()/2;

        }

    }

    private void getYCoordinates(){

        if(!isYInBounds()&&getMiddleY() > colorBounds.height()/2){

            targetY = (colorBounds.bottom-target.getHeight()/2)-1;

        }else if(!isYInBounds()&&getMiddleY() < colorBounds.height()/2){

            targetY = colorBounds.top-target.getHeight()/2;

        }

    }




    public interface onColorChoosen{

        void onColorChoosen(int color);

    }




}
