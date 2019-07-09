# AnimatedColorPicker

This is a color picker library for Android. It is used to pick from an array of colors. 

# Custom Transitions

Custom transition animations can be used to create additional inputs after the user selects a color.
The library includes a basic ColorNamingState that can be used to receieve a name for a color.
To create a new animation all you have to do is subclass BaseState and fill out the required, animation detatils.

# Usage

~~~

ColorPickerPopUp colorPickerPopUp = new ColorPickerPopUp(this); // Creates a blank color picker with no state transitions

~~~

~~~
ColorPickerPopUp colorPickerPopUp = new ColorPickerPopUp(this); 
colorPickerPopUp.addState(new ColorNamingState(colorPickerPopUp)); // Creates an animation to name the color.
~~~

# Adding to your project
Add the jitpack respository to your build.gradle file
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
