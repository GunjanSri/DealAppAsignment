package com.nagarro.dealapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nagarro.dealapplication.databinding.ActivityLoginBinding;
import com.nagarro.dealapplication.fragment.DialogFragment;
import com.nagarro.dealapplication.fragment.ResultDialogFragment;
import com.nagarro.dealapplication.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements ResultDialogFragment.OnActionComplete{

    private static final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        LoginViewModel viewModel = new LoginViewModel(this);
        binding.setLoginViewModel(viewModel);

        setUpFirebaseAuth();
    }

    private void setUpFirebaseAuth(){
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Log.d(TAG , "onAuthStateChanged: signed_in" + user.getUid());
                }else{
                    Log.d(TAG , "onAuthStateChanged: signed_out");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onOk() {
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.dismissResultFragment(getFragmentManager());
    }
}
