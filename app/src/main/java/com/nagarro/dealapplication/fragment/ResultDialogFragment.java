package com.nagarro.dealapplication.fragment;

import android.content.Context;
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

public class ResultDialogFragment extends DefaultDialogFragment implements View.OnClickListener {

    @BindView(R.id.lock_status_header)
    TextView lockStatusHeader;

    @BindView(R.id.lock_status_view_spinner)
    ImageView lockStatusViewSpinner;

    @BindView(R.id.lock_status_message)
    TextView lockStatusMessage;

    @BindView(R.id.status_button_layout)
    LinearLayout statusButtonLayout;

    @BindView(R.id.lock_status_action)
    Button lockStatusAction;

    private final AnimationFactory animationFactory = new AnimationFactory();
    private String errorMessage;
    private static final String TAG = ProgressDialogFragment.class.getSimpleName();
    private static final String ERROR_MESSAGE = "error_message";

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
        }
        lockStatusAction.setOnClickListener(this);
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
        lockStatusAction.setVisibility(View.VISIBLE);

        lockStatusMessage.setText(errorMessage);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
