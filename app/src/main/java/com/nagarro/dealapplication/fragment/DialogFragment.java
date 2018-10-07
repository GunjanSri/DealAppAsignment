package com.nagarro.dealapplication.fragment;

import android.app.FragmentManager;
import android.os.Bundle;

public class DialogFragment {

    private static final String PROGRESS_DIALOG_TAG = "progress_dialog";
    private static final String AUTHENTICATION_FAILURE_DIALOG_TAG = "authentication_failure";
    private static final String ERROR_MESSAGE = "error_message";

    public void showProgressDailog(FragmentManager fragmentManager){
        final ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.show(fragmentManager , PROGRESS_DIALOG_TAG);
    }

    public void showFailureDialog(FragmentManager fragmentManager , String errorMessage){
        final ResultDialogFragment dialogFragment = new ResultDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ERROR_MESSAGE , errorMessage);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(fragmentManager , AUTHENTICATION_FAILURE_DIALOG_TAG);
       // dialogFragment.showError();
    }

    public void dismiss(FragmentManager fragmentManager){
        if(fragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG) != null && fragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG).isVisible()){
            ((ProgressDialogFragment)fragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG)).dismiss();
        }
    }
}
