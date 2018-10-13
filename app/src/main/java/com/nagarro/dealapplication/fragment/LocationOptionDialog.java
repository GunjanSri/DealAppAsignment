package com.nagarro.dealapplication.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nagarro.dealapplication.AnimationFactory;
import com.nagarro.dealapplication.R;
import com.nagarro.dealapplication.storage.CategoryStorage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationOptionDialog extends DefaultDialogFragment implements ResultDialogFragment.OnActionComplete,
        LocationListener{

    @BindView(R.id.currentLocationRadio)
    RadioButton currentLocationRadio;

    @BindView(R.id.setLocationRadio)
    RadioButton setLocationRadio;

    @BindView(R.id.continue_action)
    Button continueAction;

    private static final String TAG = LocationOptionDialog.class.getSimpleName();
    private static final long MIN_DISTANCE_FOR_UPDATE = 10;
    private static final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2;
    LocationOptionDialog.OnSlectLocation onActionComplete;
    private DialogFragment dialogFragment;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.location_option_dialog, container, false);
        ButterKnife.bind(this, view);
        dialogFragment = new DialogFragment();
        setCancelable(false);
        return view;
    }

    @OnClick(R.id.currentLocationRadio)
    public void currentLocationSelected() {
        currentLocationRadio.toggle();
    }

    @OnClick(R.id.setLocationRadio)
    public void setLocationSelected() {
        setLocationRadio.toggle();
        if(setLocationRadio.isChecked()){
            dialogFragment.showSelectLocationDialog(getFragmentManager());
        }
    }

    @OnClick(R.id.continue_action)
    public void onContinue() {
        if (currentLocationRadio.isChecked()) {
            getCurrentLocation();
        }else{
            onActionComplete.locationSelected();
        }
    }

    private static final int REQUEST_LOCATION = 0;
    public void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity() ,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
                    },REQUEST_LOCATION);
        }else{
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(
                    Context.LOCATION_SERVICE);

            if(locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, this);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                getAddressFromLatLong(location.getLatitude() , location.getLongitude());
            }
        }
    }

    private void getAddressFromLatLong(double lat , double lon){
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        String result = null;
        try {
            List<Address> addressList = geocoder.getFromLocation(
                    lat, lon, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                result = address.getLocality();
                CategoryStorage storage = new CategoryStorage(getActivity());
                storage.saveLocation(result);
                onActionComplete.locationSelected();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        if (activity instanceof LocationOptionDialog.OnSlectLocation) {
            onActionComplete = (LocationOptionDialog.OnSlectLocation) activity;
        }
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onOk() {
        dialogFragment.dismissResultFragment(getFragmentManager());
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public interface OnSlectLocation{
        void locationSelected();
    }
}
