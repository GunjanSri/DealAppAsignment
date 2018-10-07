package com.nagarro.dealapplication;

import android.view.View;

public abstract class Animation {
    private final View view;

    Animation(View view) {
        this.view = view;
    }

    public abstract void doAnimation();

    View getView() {
        return view;
    }
}
