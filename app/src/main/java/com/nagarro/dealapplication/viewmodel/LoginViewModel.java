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
import com.nagarro.dealapplication.Storage;
import com.nagarro.dealapplication.database.CategoriesDatabase;
import com.nagarro.dealapplication.database.ReadDataCompleteListener;
import com.nagarro.dealapplication.fragment.DialogFragment;
import com.nagarro.dealapplication.model.SingleCategory;
import com.nagarro.dealapplication.util.Utility;

import java.util.Map;

public class LoginViewModel extends BaseObservable implements ReadDataCompleteListener{

    private static final String TAG = LoginViewModel.class.getSimpleName();
    private String emailAddress;
    private String password;
    private Activity context;
    DialogFragment dialogFragment;

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
        dialogFragment = new DialogFragment();
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
                                 CategoriesDatabase.getDataFromDb(LoginViewModel.this);
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

    private void readDataFromDb(){
        Map<String , SingleCategory> categoriesMap = CategoriesDatabase.getDataFromDb(this);
    }

    public void openRegisterScreen(){
        Log.d(TAG , "Register button clicked ...");
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void isSuccessful(Map<String , SingleCategory> categoriesMap) {
        Storage.getInstance(context).saveCategories(categoriesMap);
        Intent intent = new Intent(context, CategoryListActivity.class);
        context.startActivity(intent);
        dialogFragment.dismiss(context.getFragmentManager());
        context.finish();
    }
}