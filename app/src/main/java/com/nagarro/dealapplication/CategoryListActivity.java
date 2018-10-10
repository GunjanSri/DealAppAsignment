package com.nagarro.dealapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.nagarro.dealapplication.adapter.CategoryListAdapter;
import com.nagarro.dealapplication.analytics.FbTracker;
import com.nagarro.dealapplication.database.CategoriesDatabase;
import com.nagarro.dealapplication.model.Category;
import com.nagarro.dealapplication.model.Coupon;
import com.nagarro.dealapplication.model.SingleCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        categoryAdapter = new CategoryListAdapter(this);

        recyclerView = findViewById(R.id.offerCategoryListView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        recyclerView.setAdapter(categoryAdapter);

        FbTracker.getAnalyticsInstance(this);

        FbTracker.trackCurrentActivity(this,TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        menu.findItem(R.id.menu_about).setIcon(R.drawable.asterix_about_white);
        return super.onCreateOptionsMenu(menu);
    }
}
