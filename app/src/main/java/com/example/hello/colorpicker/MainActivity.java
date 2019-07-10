package com.example.hello.colorpicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hello.colorpicker.AnimationStates.ColorNamingState;
import com.example.hello.colorpicker.AnimationStates.ColorSelectionState;
import com.example.hello.colorpicker.AnimationStates.OnColorNamed;
import com.example.hello.colorpicker.AnimationStates.OnColorSelected;
import com.example.hello.colorpicker.PopUps.ColorPickerPopUp;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorPickerPopUp colorPickerPopUp = new ColorPickerPopUp(this);

        colorPickerPopUp.displayAlertDialog(new ColorSelectionState(colorPickerPopUp, new OnColorSelected() {
            @Override
            public void onColorSelected(int color) {

                Toast.makeText(MainActivity.this,String.valueOf(color),Toast.LENGTH_SHORT).show();

            }
        }));


        /*
        colorPickerPopUp.addState(new ColorNamingState(colorPickerPopUp, new OnColorNamed() {
            @Override
            public void OnColorNamed(String name) {
                Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();

            }
        }));
        */





    }


    }



