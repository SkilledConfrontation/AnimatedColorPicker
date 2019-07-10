package com.example.hello.colorpicker.AnimationStates;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hello.colorpicker.PopUps.ColorPickerPopUp;
import com.example.hello.colorpicker.R;

public class ColorNamingState extends BaseState {

    private EditText colorName;
    private float colorDemoY;
    private OnColorNamed onColorNamed;

    public ColorNamingState(ColorPickerPopUp colorPickerPopUp,OnColorNamed onColorNamed) {
        super(colorPickerPopUp);
        colorName = colorPickerPopUp.getColorName();
        this.onColorNamed = onColorNamed;
    }

    private float getColorNamePosition(){

        return (getColorPickerPopUp().getView().getHeight()/2f)- getPaddingOfColorName();

    }

    private int getPaddingOfColorName(){

        return getColorPickerPopUp().getContext().getResources().getDimensionPixelSize(R.dimen.colorNameDrawablePadding)*2;

    }


    private AnimatorSet setUpColorNameEnterAnimation(){

        ObjectAnimator animateY = animateY(getColorNamePosition(),colorName);
        animateY.setDuration(1000);

        ObjectAnimator animateAlpha = animateAlpha(0,1,colorName);

        AnimatorSet animatorSet = new AnimatorSet();

        colorName.setAlpha(0);
        colorName.setVisibility(View.VISIBLE);


        animatorSet.play(animateY).with(animateAlpha);

        return animatorSet;

    }


    @Override
    public AnimatorSet getExitAnimation() {

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.play(createNextButtonFadeOut()).with(createColorNameColorDemoExitAnimation());

        return animatorSet;

    }



    @Override
    public void onDone() {

        EditText editText = getColorPickerPopUp().getColorName();

        String enteredName = editText.getText().toString();

        onColorNamed.OnColorNamed(enteredName);

    }

    @Override
    public AnimatorSet getEnterAnimation() {

        colorDemoY = getColorPickerPopUp().getColorDemo().getY();

        float colorDemoPosition = getColorNamePosition()+colorName.getHeight()+ getPaddingOfColorName();

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator  colorDemoAnimation = animateY(colorDemoPosition,getColorPickerPopUp().getColorDemo());

        animatorSet.play(colorDemoAnimation).with(setUpColorNameEnterAnimation()).with(createButtonEnterAnimation());

        return animatorSet;

    }



    private AnimatorSet createColorNameColorDemoExitAnimation(){

        ObjectAnimator alpha = animateAlpha(1,0,colorName);
        ObjectAnimator moveY = animateY(0-colorName.getHeight()- getPaddingOfColorName(),colorName);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.play(alpha).with(moveY);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                colorName.setVisibility(View.INVISIBLE);
                colorName.setAlpha(0);
                colorName.setText("");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        ObjectAnimator colorDemo = animateY(colorDemoY,getColorPickerPopUp().getColorDemo());
        AnimatorSet colorDemoAndColorName = new AnimatorSet();

        colorDemoAndColorName.play(animatorSet).with(colorDemo);


        return colorDemoAndColorName;

    }


    private AnimatorSet createButtonEnterAnimation(){

        final Button next = getColorPickerPopUp().getNextButton();

        ObjectAnimator fadeNextOut = animateAlpha(1,0,next);
        ObjectAnimator fadeNextIn = animateAlpha(0,1,next);

        fadeNextOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                getColorPickerPopUp().setNextButtonText("Confirm Name");

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        AnimatorSet animatorSet = new AnimatorSet();



        animatorSet.play(fadeNextIn).with(createPreviousButtonFadeIn()).after(fadeNextOut);

        return animatorSet;

    }

    private ObjectAnimator createPreviousButtonFadeIn(){

        Button previous = getColorPickerPopUp().getPreviousButton();

        final ObjectAnimator fadePreviousIn = animateAlpha(0,1,previous);

        previous.setAlpha(0);
        previous.setVisibility(View.VISIBLE);

        return fadePreviousIn;

    }

    private ObjectAnimator createPreviousButtonFadeOut(){

        final Button previous = getColorPickerPopUp().getPreviousButton();

        ObjectAnimator fadePreviousOut = animateAlpha(1,0,previous);

        fadePreviousOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                previous.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        return fadePreviousOut;

    }



    private AnimatorSet createNextButtonFadeOut(){

        final Button next = getColorPickerPopUp().getNextButton();

        ObjectAnimator fadeOut = animateAlpha(1,0,next);
        ObjectAnimator fadeIn = animateAlpha(0,1,next);

        fadeOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                next.setText("Select Color");

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


       AnimatorSet animatorSet = new AnimatorSet();


       animatorSet.play(fadeOut).with(createPreviousButtonFadeOut()).before(fadeIn);

        return animatorSet;

    }



}
