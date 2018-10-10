package com.nagarro.dealapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nagarro.dealapplication.fragment.DialogFragment;
import com.nagarro.dealapplication.fragment.ResultDialogFragment;
import com.nagarro.dealapplication.storage.AppStateStorage;
import com.nagarro.dealapplication.storage.CategoryStorage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity implements ResultDialogFragment.OnActionComplete{

    @BindView(R.id.version_information)
    TextView versionInfo;
    @BindView(R.id.registered_email)
    TextView registeredEmail;
    @BindView(R.id.unregister)
    TextView unregister;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        versionInfo.setText(String.format(getResources().getString(R.string.version_information),
                BuildConfig.VERSION_NAME));
        registeredEmail.setText(String.format(getResources().getString(R.string.user),
                new AppStateStorage(this).getEmailId()));
        dialogFragment = new DialogFragment();
    }

    @OnClick(R.id.unregister)
    public void unregister() {
        dialogFragment.showFailureDialog(getFragmentManager() , getResources().getString(R.string.unregister_confirmation),true);
    }

    private void clearStorage(){
        new AppStateStorage(this).clear();
        new CategoryStorage(this).clear();
    }

    @Override
    public void onCancel() {
        dialogFragment.dismissResultFragment(getFragmentManager());
    }

    @Override
    public void onOk() {
        new AppStateStorage(this).setEmaild("");
        new AppStateStorage(this).setState(AppStateStorage.State.UNREGISTERED);

        clearStorage();
        Intent intent = new Intent(this , LoginActivity.class);
        finishAffinity();
        startActivity(intent);
    }
}
