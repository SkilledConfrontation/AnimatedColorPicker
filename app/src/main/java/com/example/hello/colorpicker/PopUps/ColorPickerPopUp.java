package com.example.hello.colorpicker.PopUps;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;

import com.example.hello.colorpicker.AnimationStates.BaseState;
import com.example.hello.colorpicker.AnimationStates.ColorSelectionState;
import com.example.hello.colorpicker.R;
import com.example.hello.colorpicker.Views.ColorDemo;
import com.example.hello.colorpicker.Views.ColorSelectionLeft;
import com.example.hello.colorpicker.Views.ColorSelectionSquare;

import java.util.ArrayList;

public class ColorPickerPopUp  {

    private Context context;
    private View popUpView;

    private ColorDemo colorDemo;
    private ColorSelectionSquare colorSelectionSquare;
    private ColorSelectionLeft colorSelectionLeft;


    private Button close;
    private Button previous;
    private EditText colorName;

    private AlertDialog alertDialog;

    private boolean nextStateAlreadyAdded = false;



    // State design pattern starts here
    private BaseState currentState;

    private ColorSelectionState colorSelectionState;

    private ArrayList<BaseState> states;
    private int currentStateIndex = 0;

    private boolean stillTranstioning =false;



    public ColorPickerPopUp(Context context){

        this.context = context;
        setUpView();
        displayAlertDialog();


    }



    private void displayAlertDialog(){

        findViews();

        createAlertDialog();

        createFirstState();

    }

    private void createFirstState(){

        colorSelectionState = new ColorSelectionState(ColorPickerPopUp.this);

        currentState =colorSelectionState;

        states = new ArrayList<>();

        states.add(colorSelectionState);

    }

    private void findViews(){

        colorSelectionLeft = (ColorSelectionLeft) popUpView.findViewById(R.id.colorSelectionRight);

        colorSelectionSquare = (ColorSelectionSquare) popUpView.findViewById(R.id.colorSelectionSquare);

        colorDemo = (ColorDemo) popUpView.findViewById(R.id.colorSelectionDemo);

        close = (Button) popUpView.findViewById(R.id.close);

        previous = (Button) popUpView.findViewById(R.id.previous);

        colorName = popUpView.findViewById(R.id.colorName);

    }

    private void createAlertDialog(){


        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setView(popUpView);

        alertDialog = alertDialogBuilder.create();

        alertDialog.show();



    }

    private void setUpView(){

        popUpView = LayoutInflater.from(context).inflate(R.layout.pop_up_dialog,null);

        popUpView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                if(!nextStateAlreadyAdded){

                    getNextButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });



                }

                getNextButton().setOnClickListener(nextStateOnClick);
                getPreviousButton().setOnClickListener(previousStateOnClick);


                popUpView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });








    }


    public ColorSelectionSquare getColorSelectionSquare(){

        return colorSelectionSquare;

    }

    public ColorSelectionLeft getColorSelectionLeft(){

        return colorSelectionLeft;

    }

    public ColorDemo getColorDemo(){

        return colorDemo;

    }

    public Button getNextButton(){

        return close;

    }

    public Button getPreviousButton(){

        return previous;

    }

    public EditText getColorName(){

        return colorName;

    }

    public View getView(){

        return popUpView;

    }

    public Context getContext(){

        return context;

    }

    private View.OnClickListener nextStateOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            boolean shouldExit = currentStateIndex == 0 && states.size() ==1 || states.size()-1 == currentStateIndex;

            if(shouldExit){

                alertDialog.dismiss();

                return;

            }


            if(states.size() > 1){


                 currentStateIndex+=1;

                playTransitionAnimation(states.get(currentStateIndex));

            }




        }
    };

    private View.OnClickListener previousStateOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(states.size() >=2){



              currentStateIndex-=1;

                playTransitionAnimation(states.get(currentStateIndex));

            }


        }
    };


    // Wait for animation to be done before modifying the index value.

    public void addState(BaseState baseState){

        states.add(baseState);

    }


    private void playTransitionAnimation(BaseState baseState){

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.play(baseState.getEnterAnimation()).with(currentState.getExitAnimation());

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                stillTranstioning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                stillTranstioning = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        currentState = baseState;

        animatorSet.start();

    }

    public void setNextButtonText(String text){

        if(states.size()-1 == currentStateIndex || stillTranstioning){

            getNextButton().setText("Finish");

        }else{

            getNextButton().setText(text);

        }

    }



}
