package com.nagarro.dealapplication.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Window;

import com.nagarro.dealapplication.R;

public class DefaultDialogFragment extends DialogFragment {

    private static final float DIM_AMOUNT = 0.6F;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialog.getWindow().setDimAmount(DIM_AMOUNT);
        return dialog;
    }
}
