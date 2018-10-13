package com.nagarro.dealapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.nagarro.dealapplication.R;
import com.nagarro.dealapplication.storage.CategoryStorage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectLocationDialog extends DefaultDialogFragment {

    @BindView(R.id.gurgaonLocation)
    RadioButton gurgaonLocation;
    @BindView(R.id.delhiLocation)
    RadioButton delhiLocation;
    @BindView(R.id.noidaLocation)
    RadioButton noidaLocation;
    @BindView(R.id.bangaloreLocation)
    RadioButton bangaloreLocation;
    @BindView(R.id.ok_action)
    Button okButton;

    private DialogFragment dialogFragment;
    private CategoryStorage storage;
    private String selectedlocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.choose_location, container, false);
        ButterKnife.bind(this, view);
        dialogFragment = new DialogFragment();
        storage = new CategoryStorage(getActivity());
        setCancelable(false);
        return view;
    }

    @OnClick(R.id.gurgaonLocation)
    public void gurgaonSelected() {
        gurgaonLocation.toggle();
        selectedlocation = "gurgaon";
    }

    @OnClick(R.id.noidaLocation)
    public void noidaSelected() {
        noidaLocation.toggle();
        selectedlocation = "noida";
    }

    @OnClick(R.id.delhiLocation)
    public void delhiSelected() {
        delhiLocation.toggle();
        selectedlocation = "new delhi";
    }

    @OnClick(R.id.bangaloreLocation)
    public void bangaloreSelected() {
        bangaloreLocation.toggle();
        selectedlocation = "bangalore";
    }

    @OnClick(R.id.ok_action)
    public void dismissDialog() {
        dismiss();
        storage.saveLocation(selectedlocation);
    }
}
