package com.example.hello.colorpicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hello.colorpicker.AnimationStates.ColorNamingState;
import com.example.hello.colorpicker.AnimationStates.TestyState;
import com.example.hello.colorpicker.PopUps.ColorPickerPopUp;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorPickerPopUp colorPickerPopUp = new ColorPickerPopUp(this);

        colorPickerPopUp.addState(new ColorNamingState(colorPickerPopUp));

    }


    }



