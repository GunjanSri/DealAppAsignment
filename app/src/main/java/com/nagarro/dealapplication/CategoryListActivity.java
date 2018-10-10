package com.nagarro.dealapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.nagarro.dealapplication.adapter.CategoryListAdapter;
import com.nagarro.dealapplication.analytics.FbTracker;
import com.nagarro.dealapplication.database.CategoriesDatabase;
import com.nagarro.dealapplication.database.ReadDataCompleteListener;
import com.nagarro.dealapplication.fragment.DialogFragment;
import com.nagarro.dealapplication.model.SingleCategory;
import com.nagarro.dealapplication.storage.CategoryStorage;

import java.util.Map;

public class CategoryListActivity extends AppCompatActivity implements ReadDataCompleteListener {

    private static final String TAG = CategoryListActivity.class.getSimpleName();
    RecyclerView recyclerView;
    private CategoryStorage storage;

    CategoryListAdapter categoryAdapter;
    DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        storage = new CategoryStorage(this);
        dialogFragment = new DialogFragment();
        categoryAdapter = new CategoryListAdapter(this);

        recyclerView = findViewById(R.id.offerCategoryListView);
        readDataFromDb();

        FbTracker.getAnalyticsInstance(this);

        FbTracker.trackCurrentActivity(this,TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        menu.findItem(R.id.menu_about).setIcon(R.drawable.asterix_about_white);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this , SettingsActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    private void readDataFromDb(){
        dialogFragment.showProgressDailog(getFragmentManager());
        CategoriesDatabase.getDataFromDb(this);
    }

    @Override
    public void isSuccessful(Map<String, SingleCategory> categoriesMap) {
        if(categoriesMap != null) {
            new CategoryStorage(this).saveCategories(categoriesMap);
            dialogFragment.dismissProgress(getFragmentManager());
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            categoryAdapter.setCategoryList(storage.getCategoryModel());
            recyclerView.setAdapter(categoryAdapter);
        }else{
            //No coupons available
        }
    }
}
