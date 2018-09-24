package com.nagarro.dealapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nagarro.dealapplication.adapter.CouponsAdapter;

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
        CouponsAdapter couponsAdapter = new CouponsAdapter(this);
        couponsAdapter.setCouponList(storage.getOffers().getCategories().get(couponCategoryPosition).getCoupons());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(couponsAdapter);
    }
}
