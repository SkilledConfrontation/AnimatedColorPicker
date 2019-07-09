package com.example.hello.colorpicker.PopUps;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hello.colorpicker.R;
import com.example.hello.colorpicker.Views.ColorDemo;
import com.example.hello.colorpicker.Views.ColorSelectionLeft;
import com.example.hello.colorpicker.Views.ColorSelectionSquare;

public class Animation {



    private ColorSelectionSquare colorSelectionSquare;
    private ColorSelectionLeft colorSelectionLeft;
    private ColorDemo colorDemo;

    private Button previous;





    private ObjectAnimator colorSelectionSquareAnimation;
    private ObjectAnimator colorDemoAnimation;
    private ObjectAnimator colorSelectionRightAnimation;
    private ObjectAnimator textViewAnimation;



    private AnimatorSet animatorSet;




    public Animation(View view){

        Log.i("INFO",String.valueOf(view.getHeight()/2));


        colorSelectionSquare = view.findViewById(R.id.colorSelectionSquare);
        colorSelectionLeft = view.findViewById(R.id.colorSelectionRight);
        colorDemo = view.findViewById(R.id.colorSelectionDemo);
        previous = view.findViewById(R.id.previous);




        colorSelectionSquareAnimation = animateColorSquare(0-colorSelectionSquare.getWidth());
        colorSelectionRightAnimation = animateColorSelectionRight(0-colorSelectionLeft.getWidth());
        colorDemoAnimation = animateColorDemo(view.getHeight()/2);
        textViewAnimation = animatePreviousButton(0,1);

        colorSelectionRightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                if(animation.getAnimatedFraction() >=0.05f && !colorDemoAnimation.isRunning()){

                    colorDemoAnimation.start();

                }


            }
        });

        animatorSet = new AnimatorSet();






    }




    public void reverse(){

        colorSelectionSquareAnimation.reverse();

        animatorSet.reverse();

    }

    public void play(){

        colorSelectionSquareAnimation.start();

        animatorSet.play(textViewAnimation).after(colorSelectionRightAnimation);

        animatorSet.start();

    }


    private ObjectAnimator animatePreviousButton(int begining, int to){

        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(previous,"alpha",begining,to);
        alphaAnimation.setDuration(1000);

        return alphaAnimation;

    }

    private ObjectAnimator animateColorSquare(int xLocation){

        ObjectAnimator colorSelectionSquareAnimation = ObjectAnimator.ofFloat(colorSelectionSquare,"x",xLocation);
        colorSelectionSquareAnimation.setDuration(1000);

        return colorSelectionSquareAnimation;

    }

    private ObjectAnimator animateColorSelectionRight(int xLocation){

        ObjectAnimator colorSelectionRight = ObjectAnimator.ofFloat(colorSelectionLeft,"x",xLocation);
        colorSelectionRight.setDuration(1000);
        colorSelectionRight.setStartDelay(350);

        return colorSelectionRight;

    }

    private ObjectAnimator animateColorDemo(int yLocation){

        ObjectAnimator moveUp = ObjectAnimator.ofFloat(colorDemo,"y",yLocation);
        moveUp.setDuration(1500);

        return moveUp;

    }








}

