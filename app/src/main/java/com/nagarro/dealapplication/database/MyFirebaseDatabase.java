package com.nagarro.dealapplication.database;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.nagarro.dealapplication.viewmodel.OfferViewModel;

import java.io.IOException;
import java.io.InputStream;

public class MyFirebaseDatabase {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Context context;

    public void getFirebaseDbInstance(Context context){
        this.context = context;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("deals");
    }

    public void writeDataToDb(){
        String json = null;
        try {
            InputStream is = context.getAssets().open("offers.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        OfferViewModel objectModel = gson.fromJson(json, OfferViewModel.class);
        databaseReference.setValue(objectModel);
    }

    public void readFromDb(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                OfferViewModel value = dataSnapshot.getValue(OfferViewModel.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
