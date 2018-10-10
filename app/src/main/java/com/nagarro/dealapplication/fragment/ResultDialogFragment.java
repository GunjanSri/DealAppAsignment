package com.nagarro.dealapplication.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nagarro.dealapplication.AnimationFactory;
import com.nagarro.dealapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultDialogFragment extends DefaultDialogFragment {

    @BindView(R.id.lock_status_header)
    TextView lockStatusHeader;

    @BindView(R.id.lock_status_view_spinner)
    ImageView lockStatusViewSpinner;

    @BindView(R.id.lock_status_message)
    TextView lockStatusMessage;

    @BindView(R.id.status_button_layout)
    LinearLayout statusButtonLayout;

    @BindView(R.id.ok_action)
    Button okAction;
    @BindView(R.id.cancel_action)
    Button cancelAction;

    private final AnimationFactory animationFactory = new AnimationFactory();
    private static final String TAG = ProgressDialogFragment.class.getSimpleName();
    private static final String ERROR_MESSAGE = "error_message";
    private static final String BUTTON_VISIBILTY = "button_visibilty";
    OnActionComplete onActionComplete;
    private String errorMessage;
    private boolean visibility;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.progress_spinner, container, false);
        ButterKnife.bind(this, view);
        if(getArguments() != null){
            errorMessage = getArguments().getString(ERROR_MESSAGE);
            visibility = getArguments().getBoolean(BUTTON_VISIBILTY);
        }

        setCancelable(false);
        showError();
        return view;
    }

    public void showError(){
        Log.d(TAG , "Authentication Failed");
        lockStatusViewSpinner.setVisibility(View.GONE);
        lockStatusHeader.setVisibility(View.GONE);
        lockStatusMessage.setVisibility(View.VISIBLE);
        statusButtonLayout.setVisibility(View.VISIBLE);
        okAction.setVisibility(View.VISIBLE);
        if(visibility)
            cancelAction.setVisibility(View.VISIBLE);
        else
            cancelAction.setVisibility(View.GONE);

        lockStatusMessage.setText(errorMessage);
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        if (activity instanceof OnActionComplete) {
            onActionComplete = (OnActionComplete) activity;
        }
    }

    @OnClick(R.id.ok_action)
    public void okAction(){
        onActionComplete.onOk();
    }

    @OnClick(R.id.cancel_action)
    public void cancelAction(){
        onActionComplete.onCancel();
    }

    public interface OnActionComplete{
        void onCancel();
        void onOk();
    }
}
