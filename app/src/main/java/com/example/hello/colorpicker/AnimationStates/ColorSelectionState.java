package com.example.hello.colorpicker.AnimationStates;



import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import com.example.hello.colorpicker.PopUps.ColorPickerPopUp;
import com.example.hello.colorpicker.Views.ColorDemo;
import com.example.hello.colorpicker.Views.ColorSelectionLeft;
import com.example.hello.colorpicker.Views.ColorSelectionSquare;


public class ColorSelectionState extends BaseState implements ColorSelectionLeft.onColorSlided,ColorSelectionSquare.onColorChoosen {

    private float colorSelectionSquareStartX;
    private float colorSelectionRightStartX;

    private ColorSelectionSquare colorSelectionSquare;
    private ColorSelectionLeft colorSelectionRight;
    private ColorDemo colorDemo;


    public ColorSelectionState(ColorPickerPopUp colorPickerPopUp) {

        super(colorPickerPopUp);

        init();

        colorSelectionSquare = getColorPickerPopUp().getColorSelectionSquare();
        colorSelectionRight = getColorPickerPopUp().getColorSelectionLeft();
        colorDemo = getColorPickerPopUp().getColorDemo();

        getColorPickerPopUp().getColorSelectionLeft().setOnColorSlided(this);
        getColorPickerPopUp().getColorSelectionSquare().setOnColorChoosen(this);

    }


    @Override
    public AnimatorSet getExitAnimation() {

        colorSelectionSquareStartX = getColorPickerPopUp().getColorSelectionSquare().getX();
        colorSelectionRightStartX = getColorPickerPopUp().getColorSelectionLeft().getX();

        ObjectAnimator colorSelectionRightAnimation = animateColorSelectionRight(0-getColorPickerPopUp().getColorSelectionSquare().getWidth());
        ObjectAnimator  colorSelectionSquareAnimation = animateColorSquare(0-getColorPickerPopUp().getColorSelectionSquare().getWidth());

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.play(colorSelectionRightAnimation).with(colorSelectionSquareAnimation);

        return animatorSet;

    }

    @Override
    public AnimatorSet getEnterAnimation() {


         AnimatorSet animatorSet = new AnimatorSet();

         ObjectAnimator colorSelectionSquareAnimation = animateColorSquare((int)colorSelectionSquareStartX);
         ObjectAnimator colorSelectionRightAnimation = animateColorSelectionRight((int)colorSelectionRightStartX);


         animatorSet.play(colorSelectionRightAnimation).with(colorSelectionSquareAnimation).after(300);

         animatorSet.addListener(new Animator.AnimatorListener() {
             @Override
             public void onAnimationStart(Animator animation) {

             }

             @Override
             public void onAnimationEnd(Animator animation) {
                 init();
             }

             @Override
             public void onAnimationCancel(Animator animation) {

             }

             @Override
             public void onAnimationRepeat(Animator animation) {

             }
         });



         return animatorSet;


    }



    // This is for after any animation

    private void init() {

        getColorPickerPopUp().getNextButton().setText("Select color");

        getColorPickerPopUp().getPreviousButton().setAlpha(0);
        getColorPickerPopUp().getPreviousButton().setVisibility(View.INVISIBLE);

        getColorPickerPopUp().getColorName().setAlpha(0);
        getColorPickerPopUp().getColorName().setVisibility(View.INVISIBLE);

    }


    @Override
    public void onColorSlide(int pixelColor) {


        colorSelectionSquare.setPaint(pixelColor);
        colorDemo.setColor(colorSelectionSquare.getCurrentColor());



    }

    @Override
    public void onColorChoosen(int color) {
        colorDemo.setColor(color);
    }
}
