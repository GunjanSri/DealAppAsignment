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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nagarro.dealapplication.CategoryListActivity;
import com.nagarro.dealapplication.R;
import com.nagarro.dealapplication.fragment.DialogFragment;
import com.nagarro.dealapplication.model.Categories;
import com.nagarro.dealapplication.model.Category;
import com.nagarro.dealapplication.model.Coupon;
import com.nagarro.dealapplication.model.SingleCategory;
import com.nagarro.dealapplication.util.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        /*Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);*/
        //saveData();
       readData();
    }

    private void saveData(){
        /*Map<String , SingleCategory> map = new HashMap<>();
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon("Swiggy" , "dsgdkshldshlhls" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/swiggy.png?alt=media&token=4cd91ddf-e016-4f40-9280-a5fe0814e4c4",
                "Gurgaon" , "31-Oct-2018" , "CODE-001"));
        coupons.add(new Coupon("Swiggynnn" , "dsgdkshldshlhls" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/swiggy.png?alt=media&token=4cd91ddf-e016-4f40-9280-a5fe0814e4c4",
                "Gurgaon" , "31-Oct-2018" , "CODE-001"));

        List<Coupon> coupons2 = new ArrayList<>();
        coupons2.add(new Coupon("Swiggy" , "dsgdkshldshlhls" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/swiggy.png?alt=media&token=4cd91ddf-e016-4f40-9280-a5fe0814e4c4",
                "Gurgaon" , "31-Oct-2018" , "CODE-001"));
        coupons2.add(new Coupon("Swiggynnn" , "dsgdkshldshlhls" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/swiggy.png?alt=media&token=4cd91ddf-e016-4f40-9280-a5fe0814e4c4",
                "Gurgaon" , "31-Oct-2018" , "CODE-001"));

        map.put("Food" , new SingleCategory("https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/swiggy.png?alt=media&token=4cd91ddf-e016-4f40-9280-a5fe0814e4c4" , coupons));

        map.put("Movies" , new SingleCategory("https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/swiggy.png?alt=media&token=4cd91ddf-e016-4f40-9280-a5fe0814e4c4" , coupons2));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("categories").setValue(new Categories(map)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG , "onComplete " + "data added successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG , "onFailure: "+ e.getMessage());
            }
        });*/


    }
    Map<String , SingleCategory> map = new HashMap<>();
    private void readData(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("categories");
        Log.d(TAG , "Reading categories from database");


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG , "onDataChange Running");
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    Log.d(TAG , "onDataChange ... singleSnapshot " + singleSnapshot.toString());
                    Categories map = singleSnapshot.getValue(Categories.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG , "onCancalled :" + databaseError.getMessage());
            }
        });
    }
}