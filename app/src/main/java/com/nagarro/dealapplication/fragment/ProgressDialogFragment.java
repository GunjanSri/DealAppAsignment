package com.nagarro.dealapplication.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
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

public class ProgressDialogFragment extends DefaultDialogFragment implements View.OnClickListener {

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

        setCancelable(false);
        showLoader();
        return view;
    }

    public void showLoader() {
        Log.d(TAG , "Showing Loader");
        lockStatusViewSpinner.setVisibility(View.VISIBLE);
        lockStatusHeader.setVisibility(View.VISIBLE);
        lockStatusMessage.setVisibility(View.GONE);
        statusButtonLayout.setVisibility(View.GONE);
        okAction.setVisibility(View.GONE);
        cancelAction.setVisibility(View.GONE);
        final TypedArray themedAttributes = getActivity().obtainStyledAttributes(
                new TypedValue().data,
                new int[]{R.attr.progressDialogSpinnerColor}
        );
        lockStatusViewSpinner.clearAnimation();
        lockStatusViewSpinner.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        animationFactory.createSpinAnimation(lockStatusViewSpinner).doAnimation();
        lockStatusHeader.setText(getActivity().getResources().getString(R.string.text_loader));
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
