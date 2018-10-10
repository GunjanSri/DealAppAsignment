package com.nagarro.dealapplication.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nagarro.dealapplication.model.Coupon;
import com.nagarro.dealapplication.model.SingleCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriesDatabase {

    private static final String TAG = CategoriesDatabase.class.getSimpleName();
    private static Map<String , SingleCategory> categoryCouponMap = new HashMap<>();
    private static List<Coupon> couponList = new ArrayList<>();
    private static String categoryIcon;
    private static int position;

    public static Map<String , SingleCategory> getDataFromDb(final ReadDataCompleteListener listener){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("categories");
        Log.d(TAG , "Reading categories from database");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG , "onDataChange " + dataSnapshot.getChildrenCount());
                Iterable<DataSnapshot> positionIterator = dataSnapshot.getChildren(); // 0

                for(DataSnapshot positionSnapshot : positionIterator){
                    Iterable<DataSnapshot> categoryIterator = positionSnapshot.getChildren();//food and movies
                    for(DataSnapshot categorySnapshot : categoryIterator){
                        String categoryName = categorySnapshot.getKey();
                        Iterable<DataSnapshot> categoryItemIterator = categorySnapshot.getChildren();// icon and coupon list
                        for(DataSnapshot categoryItem : categoryItemIterator){
                            if(position == 1)
                                categoryIcon = (String)categoryItem.getValue();
                            Iterable<DataSnapshot> couponIterator = categoryItem.getChildren();//coupon list

                            for(DataSnapshot couponSnapshot : couponIterator){
                                Coupon coupon = couponSnapshot.getValue(Coupon.class);
                                couponList.add(coupon);
                            }
                            position = 1;
                        }
                        categoryCouponMap.put(categoryName , new SingleCategory(categoryIcon , couponList));
                        couponList = new ArrayList<>();
                        position = 0;
                    }
                }

                listener.isSuccessful(categoryCouponMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG , "onCancelled :" + databaseError.getMessage());
            }
        });

        return categoryCouponMap;
    }

    private void saveData() {
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
}
