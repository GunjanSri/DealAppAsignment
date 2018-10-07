package com.nagarro.dealapplication;

import android.animation.ObjectAnimator;
import android.view.View;

public class Fade extends Animation {

    Fade(View view) {
        super(view);
    }

    @Override
    public void doAnimation() {
        final ObjectAnimator anim = ObjectAnimator.ofFloat(getView(), "alpha", 0F, 1F);
        anim.setDuration(300);
        anim.start();
    }
}
