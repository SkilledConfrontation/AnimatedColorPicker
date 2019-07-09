package com.example.hello.colorpicker.AnimationStates;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

import com.example.hello.colorpicker.PopUps.ColorPickerPopUp;

public class TestyState extends BaseState {

    private float xColorSquare;

    private float xColorSelection;


    public TestyState(ColorPickerPopUp colorPickerPopUp) {
        super(colorPickerPopUp);
    }

    @Override
    public AnimatorSet getExitAnimation() {

        getColorPickerPopUp().getColorSelectionSquare().setX(xColorSquare);
        getColorPickerPopUp().getColorSelectionLeft().setX(xColorSelection);

        AnimatorSet animatorSet = new AnimatorSet();


        ObjectAnimator animateSquare = animateAlpha(0,1,getColorPickerPopUp().getColorSelectionSquare());
        ObjectAnimator animateColorSelectionRight = animateAlpha(0,1,getColorPickerPopUp().getColorSelectionLeft());

        animatorSet.play(animateSquare).with(animateColorSelectionRight);
        return animatorSet;


    }


    @Override
    public AnimatorSet getEnterAnimation() {

        xColorSquare = getColorPickerPopUp().getColorSelectionSquare().getX();
        xColorSelection = getColorPickerPopUp().getColorSelectionSquare().getX();

        ObjectAnimator animateSquare = animateAlpha(1,0,getColorPickerPopUp().getColorSelectionSquare());
        ObjectAnimator animateColorSelectionRight = animateAlpha(1,0,getColorPickerPopUp().getColorSelectionLeft());

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.play(animateSquare).with(animateColorSelectionRight);

        getColorPickerPopUp().getNextButton().setText("Come back");

        return animatorSet;


    }
}
