package com.nagarro.dealapplication;


import android.view.View;

public class AnimationFactory {

    public Spin createSpinAnimation(View view) {
        return new Spin(view);
    }

    public Fade createFadeAnimation(View view) {
        return new Fade(view);
    }
}
