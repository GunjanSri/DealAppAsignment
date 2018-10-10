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
import com.nagarro.dealapplication.fragment.ResultDialogFragment;
import com.nagarro.dealapplication.storage.AppStateStorage;
import com.nagarro.dealapplication.storage.CategoryStorage;
import com.nagarro.dealapplication.database.CategoriesDatabase;
import com.nagarro.dealapplication.database.ReadDataCompleteListener;
import com.nagarro.dealapplication.fragment.DialogFragment;
import com.nagarro.dealapplication.model.SingleCategory;
import com.nagarro.dealapplication.util.Utility;

import java.util.Map;

public class LoginViewModel extends BaseObservable implements ResultDialogFragment.OnActionComplete{

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
        if (emailAddress == null) {
            Utility.showToastMessage(context, R.string.error_invalid_email_address);
        } else if (!Utility.validEmailAddress(emailAddress)) {
            Log.d(TAG, "Invalid Email Address: " + emailAddress);
            Utility.showToastMessage(context, R.string.error_invalid_email_address);
        } else if (password == null) {
            Log.d(TAG, "Invalid Password: " + password);
            Utility.showToastMessage(context, R.string.error_invalid_password);
        } else {
            dialogFragment = new DialogFragment();
            dialogFragment.showProgressDailog(context.getFragmentManager());

            Log.d(TAG, "Sign In Started ...");
            FirebaseAuth.getInstance().signInWithEmailAndPassword(emailAddress, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "onComplete: " + "authentication completed");
                            if (task.isSuccessful()) {
                                new AppStateStorage(context).setState(AppStateStorage.State.REGISTERED);
                                new AppStateStorage(context).setEmaild(emailAddress);

                                dialogFragment.dismissProgress(context.getFragmentManager());
                                launchCategoryListActivity();
                                context.finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + "authentication failed");
                    dialogFragment.dismissProgress(context.getFragmentManager());
                    dialogFragment.showFailureDialog(context.getFragmentManager(),
                            context.getResources().getString(R.string.error_authentication_failed), false);
                }
            });
        }
    }

    public void openRegisterScreen(){
        Log.d(TAG , "Register button clicked ...");
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    private void launchCategoryListActivity(){
        Intent intent = new Intent(context, CategoryListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onOk() {
        dialogFragment.dismissResultFragment(context.getFragmentManager());
    }
}