package com.nagarro.dealapplication;

import android.view.View;
import android.view.animation.AnimationUtils;

public class Spin extends Animation {

    Spin(View view) {
        super(view);
    }

    @Override
    public void doAnimation() {
        getView().startAnimation(AnimationUtils.loadAnimation(getView().getContext(), R.anim.spin_animation));
    }
}
