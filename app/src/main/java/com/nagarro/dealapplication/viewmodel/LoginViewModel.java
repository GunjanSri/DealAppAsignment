package com.nagarro.dealapplication.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nagarro.dealapplication.CategoryListActivity;
import com.nagarro.dealapplication.R;
import com.nagarro.dealapplication.RegisterActivity;
import com.nagarro.dealapplication.fragment.DialogFragment;
import com.nagarro.dealapplication.fragment.ProgressDialogFragment;
import com.nagarro.dealapplication.util.Utility;

public class LoginViewModel extends BaseObservable{

    private static final String TAG = LoginViewModel.class.getSimpleName();
    private String emailAddress;
    private String password;
    private Activity context;

    public LoginViewModel(Activity context){
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

    public void checkAuthenticatedUser(){
        final DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.showProgressDailog(context.getFragmentManager());

         if(!Utility.validEmailAddress(emailAddress)){
             Log.d(TAG , "Invalid Email Address: " + emailAddress);
             Utility.showToastMessage(context,R.string.error_invalid_email_address);
         } else {
             Log.d(TAG, "Sign In Started ...");
             FirebaseAuth.getInstance().signInWithEmailAndPassword(emailAddress, password)
                     .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             Log.d(TAG, "onComplete: " + "authentication completed");
                             if (task.isSuccessful()) {
                                 Intent intent = new Intent(context, CategoryListActivity.class);
                                 context.startActivity(intent);
                                 dialogFragment.dismiss(context.getFragmentManager());
                                 context.finish();
                             }
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             Log.d(TAG, "onFailure: " + "authentication failed");
                             Utility.showToastMessage(context, R.string.error_authentication_failed);
                             dialogFragment.dismiss(context.getFragmentManager());
                             dialogFragment.showFailureDialog(context.getFragmentManager() ,
                                     context.getResources().getString(R.string.error_authentication_failed));
                         }
                     });
         }
    }

    public void openRegisterScreen(){
        Log.d(TAG , "Register button clicked ...");
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}