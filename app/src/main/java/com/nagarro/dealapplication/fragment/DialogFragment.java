package com.nagarro.dealapplication.fragment;

import android.app.FragmentManager;
import android.os.Bundle;

public class DialogFragment {

    private static final String PROGRESS_DIALOG_TAG = "progress_dialog";
    private static final String AUTHENTICATION_FAILURE_DIALOG_TAG = "authentication_failure";
    private static final String LOCATION_DIALOG_TAG = "location_dialog";
    private static final String SELECT_LOCATION_TAG = "select_location";
    private static final String ERROR_MESSAGE = "error_message";
    private static final String BUTTON_VISIBILTY = "button_visibilty";

    public void showProgressDailog(FragmentManager fragmentManager){
        final ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.show(fragmentManager , PROGRESS_DIALOG_TAG);
    }

    public void showFailureDialog(FragmentManager fragmentManager , String errorMessage , boolean visbility){
        final ResultDialogFragment dialogFragment = new ResultDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ERROR_MESSAGE , errorMessage);
        bundle.putBoolean(BUTTON_VISIBILTY , visbility);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(fragmentManager , AUTHENTICATION_FAILURE_DIALOG_TAG);
    }

    public void dismissProgress(FragmentManager fragmentManager){
        if(fragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG) != null && fragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG).isVisible()){
            ((ProgressDialogFragment)fragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG)).dismiss();
        }
    }

    public void dismissResultFragment(FragmentManager fragmentManager){
        if(fragmentManager.findFragmentByTag(AUTHENTICATION_FAILURE_DIALOG_TAG) != null && fragmentManager.
                findFragmentByTag(AUTHENTICATION_FAILURE_DIALOG_TAG).isVisible()){
            ((ResultDialogFragment)fragmentManager.findFragmentByTag(AUTHENTICATION_FAILURE_DIALOG_TAG)).dismiss();
        }
    }

    public void showLocationDialog(FragmentManager fragmentManager){
        LocationOptionDialog dialog = new LocationOptionDialog();
        dialog.show(fragmentManager , LOCATION_DIALOG_TAG);
    }

    public void dismissLocationDialog(FragmentManager fragmentManager){
        if(fragmentManager.findFragmentByTag(LOCATION_DIALOG_TAG) != null && fragmentManager.
                findFragmentByTag(LOCATION_DIALOG_TAG).isVisible()){
            ((LocationOptionDialog)fragmentManager.findFragmentByTag(LOCATION_DIALOG_TAG)).dismiss();
        }
    }

    public void showSelectLocationDialog(FragmentManager fragmentManager){
        SelectLocationDialog dialog = new SelectLocationDialog();
        dialog.show(fragmentManager , SELECT_LOCATION_TAG);
    }

    public void dismissSelectLocationDialog(FragmentManager fragmentManager){
        if(fragmentManager.findFragmentByTag(SELECT_LOCATION_TAG) != null && fragmentManager.
                findFragmentByTag(SELECT_LOCATION_TAG).isVisible()){
            ((SelectLocationDialog)fragmentManager.findFragmentByTag(SELECT_LOCATION_TAG)).dismiss();
        }
    }
}
