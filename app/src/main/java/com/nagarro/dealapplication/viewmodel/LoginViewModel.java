package com.nagarro.dealapplication.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.widget.Toast;

import com.nagarro.dealapplication.R;
import com.nagarro.dealapplication.RegisterActivity;
import com.nagarro.dealapplication.util.Utility;

import okhttp3.internal.Util;

public class LoginViewModel extends BaseObservable {

    private static final String TAG = LoginViewModel.class.getSimpleName();
    private String emailAddress;
    private String password;
    private Context context;

    public LoginViewModel(Context context){
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
         if(!Utility.validEmailAddress(emailAddress)){
             Log.d(TAG , "Invalid Email Address: " + emailAddress);
             Utility.showToastMessage(context,R.string.error_invalid_email_address);
         }else{
             Log.d(TAG , "SIgn In Started ...");

         }
    }

    public void openRegisterScreen(){
        Log.d(TAG , "Register button clicked ...");
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}