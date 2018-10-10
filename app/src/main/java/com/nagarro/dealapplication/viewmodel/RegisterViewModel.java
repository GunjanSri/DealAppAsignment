package com.nagarro.dealapplication.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nagarro.dealapplication.R;
import com.nagarro.dealapplication.fragment.DialogFragment;
import com.nagarro.dealapplication.fragment.ProgressDialogFragment;
import com.nagarro.dealapplication.fragment.ResultDialogFragment;
import com.nagarro.dealapplication.util.Utility;

public class RegisterViewModel extends BaseObservable implements ResultDialogFragment.OnActionComplete {

    private static final String TAG = RegisterViewModel.class.getSimpleName();
    private String emailAddress;
    private String password;
    private String confirmPassword;
    private Activity context;
    private DialogFragment dialogFragment;

    public RegisterViewModel(Activity context){
        this.context = context;
    }

    @Bindable
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bindable
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void registerUser(){
        if(emailAddress == null){
            Log.d(TAG , "Invalid email Address: " + emailAddress );
            Utility.showToastMessage(context,R.string.error_invalid_email_address);
        }else if(!Utility.validEmailAddress(emailAddress)){
            Log.d(TAG , "Invalid email Address: " + emailAddress );
            Utility.showToastMessage(context,R.string.error_invalid_email_address);
        }else if(password == null){
            Log.d(TAG , "Invalid Password: " + password );
            Utility.showToastMessage(context,R.string.error_invalid_password);
        } else if(! password.equals(confirmPassword)){
            Log.d(TAG , "Password not matching: " + "Password: " + password + "Confirm Passowrd: " +
                                                            confirmPassword);
            Utility.showToastMessage(context,R.string.error_password_not_matching);
        }else{
            Log.d(TAG , "Registration started ...");
            registerNewEmail();
        }
    }

    private void registerNewEmail(){
        dialogFragment = new DialogFragment();
        dialogFragment.showProgressDailog(context.getFragmentManager());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG , "onComplete: onComplete: " + task.isSuccessful());
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: AuthState: " + FirebaseAuth.getInstance().
                                    getCurrentUser().getUid());

                            FirebaseAuth.getInstance().signOut();
                            dialogFragment.dismissProgress(context.getFragmentManager());
                            context.finish();
                        } else {
                            Log.w(TAG, "onComplete: failure"+ task.isSuccessful());
                            Utility.showToastMessage(context , R.string.error_authentication_failed);
                            dialogFragment.dismissProgress(context.getFragmentManager());
                            dialogFragment.showFailureDialog(context.getFragmentManager() , context.getResources().getString(R.string.error_registration_failed),false);
                        }
                    }
                });
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onOk() {
        dialogFragment.dismissResultFragment(context.getFragmentManager());
    }
}
