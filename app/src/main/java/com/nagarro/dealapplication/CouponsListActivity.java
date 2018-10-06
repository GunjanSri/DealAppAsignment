package com.nagarro.dealapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nagarro.dealapplication.adapter.CouponListAdapter;
import com.nagarro.dealapplication.model.Category;
import com.nagarro.dealapplication.model.Coupon;

import java.util.ArrayList;
import java.util.List;

public class CouponsListActivity extends AppCompatActivity {

    private static final String SELECTED_CATEGORY = "selected_category";
    private int couponCategoryPosition;
    private Storage storage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        storage = Storage.getInstance(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            couponCategoryPosition = bundle.getInt(SELECTED_CATEGORY);
        }

        RecyclerView recyclerView = findViewById(R.id.offerListView);
        CouponListAdapter couponsAdapter = new CouponListAdapter(this);
        couponsAdapter.setCouponList(storeDealsInPreference().get(0).getCoupons());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(couponsAdapter);
    }

    private List<Category> storeDealsInPreference(){

        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon("Swiggy" , "dsgdkshldshlhls" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/swiggy.png?alt=media&token=4cd91ddf-e016-4f40-9280-a5fe0814e4c4",
                "Gurgaon" , "31-Oct-2018" , "CODE-001"));
        categories.add(new Category("food" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/food.png?alt=media&token=ad4778f8-757c-4dd5-baca-7ffd533f0fc8",
                coupons));

        categories.add(new Category("food" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/food.png?alt=media&token=ad4778f8-757c-4dd5-baca-7ffd533f0fc8",
                coupons));

        categories.add(new Category("movies" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/food.png?alt=media&token=ad4778f8-757c-4dd5-baca-7ffd533f0fc8",
                coupons));

        categories.add(new Category("movies" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/food.png?alt=media&token=ad4778f8-757c-4dd5-baca-7ffd533f0fc8",
                coupons));



        storage.saveOffers(categories);
        return categories;
    }
}
