package com.nagarro.dealapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nagarro.dealapplication.adapter.CategoryListAdapter;
import com.nagarro.dealapplication.analytics.FbTracker;
import com.nagarro.dealapplication.model.Category;
import com.nagarro.dealapplication.model.Coupon;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity {

    private static final String TAG = CategoryListActivity.class.getSimpleName();
    RecyclerView recyclerView;
    private Storage storage;

    CategoryListAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        storage = Storage.getInstance(this);

        readCategoryListFromDb();
        storeDealsInPreference();

        categoryAdapter = new CategoryListAdapter(this);
        categoryAdapter.setCategoryList(storeDealsInPreference());
        recyclerView = findViewById(R.id.offerCategoryListView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        recyclerView.setAdapter(categoryAdapter);

        FbTracker.getAnalyticsInstance(this);

        FbTracker.trackCurrentActivity(this,TAG);
    }

    private void readCategoryListFromDb(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("categories").orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser()
                                            .getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private List<Category> storeDealsInPreference(){

        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon("Swiggy" , "dsgdkshldshlhls" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/swiggy.png?alt=media&token=4cd91ddf-e016-4f40-9280-a5fe0814e4c4",
                "Gurgaon" , "31-Oct-2018" , "CODE-001"));
        categories.add(new Category("food" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/food.png?alt=media&token=ad4778f8-757c-4dd5-baca-7ffd533f0fc8",
                coupons));

        categories.add(new Category("movies" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/food.png?alt=media&token=ad4778f8-757c-4dd5-baca-7ffd533f0fc8",
                coupons));

        storage.saveOffers(categories);
        return categories;
    }
}
