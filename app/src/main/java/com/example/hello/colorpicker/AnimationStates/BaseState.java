package com.example.hello.colorpicker.AnimationStates;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import com.example.hello.colorpicker.PopUps.ColorPickerPopUp;


public abstract class BaseState {


    private ColorPickerPopUp colorPickerPopUp;


    public BaseState(ColorPickerPopUp colorPickerPopUp){

        this.colorPickerPopUp = colorPickerPopUp;

    }



    protected ObjectAnimator animateAlpha(int begining, int to, View view){

        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view,"alpha",begining,to);
        alphaAnimation.setDuration(1000);

        return alphaAnimation;

    }


    protected ObjectAnimator animateColorSquare(int xLocation){

        ObjectAnimator colorSelectionSquareAnimation = ObjectAnimator.ofFloat(colorPickerPopUp.getColorSelectionSquare(),"x",xLocation);
        colorSelectionSquareAnimation.setDuration(1000);

        return colorSelectionSquareAnimation;

    }

    protected ObjectAnimator animateColorSelectionRight(int xLocation){

        ObjectAnimator colorSelectionRight = ObjectAnimator.ofFloat(colorPickerPopUp.getColorSelectionLeft(),"x",xLocation);
        colorSelectionRight.setDuration(1000);

        return colorSelectionRight;

    }

    protected ObjectAnimator animateY(float yLocation,View view){

        ObjectAnimator moveUp = ObjectAnimator.ofFloat(view,"y",yLocation);
        moveUp.setDuration(800);

        return moveUp;

    }

    protected ColorPickerPopUp getColorPickerPopUp(){

        return colorPickerPopUp;

    }




    public abstract AnimatorSet getExitAnimation();


    public abstract void onDone();

    public abstract AnimatorSet getEnterAnimation();










}
