# AnimatedColorPicker

This is a color picker library for Android. It is used to pick from an array of colors. 

# Custom Transitions

Custom transition animations can be used to create additional inputs after the user selects a color.
The library includes a basic ColorNamingState that can be used to receieve a name for a color.
To create a new animation all you have to do is subclass BaseState and fill out the required, animation detatils.

# Usage




~~~
ColorPickerPopUp colorPickerPopUp = new ColorPickerPopUp(this);

  colorPickerPopUp.displayAlertDialog(new ColorSelectionState(colorPickerPopUp, new OnColorSelected() {
            @Override
            public void onColorSelected(int color) {

                Toast.makeText(MainActivity.this,String.valueOf(color),Toast.LENGTH_SHORT).show();

            }
        }));

        colorPickerPopUp.addState(new ColorNamingState(colorPickerPopUp, new OnColorNamed() {
                    @Override
                    public void OnColorNamed(String name) {
                        Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();

                    }
                }));

~~~

# Adding to your project
Add the jitpack repository to your build.gradle file
~~~

allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
~~~

To add the library

~~~

dependencies {
    implementation 'com.github.SkilledConfrontation:AnimatedColorPicker:b344b2c20d'
}

~~~
