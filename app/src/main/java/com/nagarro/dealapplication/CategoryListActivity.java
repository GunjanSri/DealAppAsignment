package com.nagarro.dealapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nagarro.dealapplication.adapter.CategoryListAdapter;
import com.nagarro.dealapplication.analytics.FbTracker;
import com.nagarro.dealapplication.database.CategoriesDatabase;
import com.nagarro.dealapplication.database.ReadDataCompleteListener;
import com.nagarro.dealapplication.fragment.DialogFragment;
import com.nagarro.dealapplication.fragment.LocationOptionDialog;
import com.nagarro.dealapplication.fragment.ResultDialogFragment;
import com.nagarro.dealapplication.model.SingleCategory;
import com.nagarro.dealapplication.storage.CategoryStorage;

import java.util.Map;

public class CategoryListActivity extends AppCompatActivity implements ReadDataCompleteListener,
        ResultDialogFragment.OnActionComplete,LocationOptionDialog.OnSlectLocation {

    private static final String TAG = CategoryListActivity.class.getSimpleName();
    RecyclerView recyclerView;
    TextView noCouponText;
    private CategoryStorage storage;
    private static int REQUEST_LOCATION = 0;

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
        noCouponText = findViewById(R.id.noCouponText);
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
            dialogFragment.dismissProgress(getFragmentManager());
            new CategoryStorage(this).saveCategories(categoriesMap);
            recyclerView.setVisibility(View.VISIBLE);
            noCouponText.setVisibility(View.GONE);
            if(!storage.isLocationShown())
                dialogFragment.showLocationDialog(getFragmentManager());
            else{
                recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
                categoryAdapter.setCategoryList(storage.getCategoryModel());
                recyclerView.setAdapter(categoryAdapter);
            }

        }else{
            recyclerView.setVisibility(View.GONE);
            noCouponText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == REQUEST_LOCATION){
            if(grantResults.length == 2){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    new LocationOptionDialog().getCurrentLocation();
                }else{
                    dialogFragment.showFailureDialog(getFragmentManager() ,
                            getResources().getString(R.string.error_location_permission_denied),
                            false);
                }
            }else{
                dialogFragment.showFailureDialog(getFragmentManager() ,
                        getResources().getString(R.string.error_location_permission_denied),
                        false);
            }
        }
    }

    @Override
    public void onCancel() { }

    @Override
    public void onOk() {
        dialogFragment.dismissResultFragment(getFragmentManager());
        dialogFragment.dismissLocationDialog(getFragmentManager());
    }

    @Override
    public void locationSelected() {
        storage.setLocationShown(true);
        dialogFragment.dismissLocationDialog(getFragmentManager());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        categoryAdapter.setCategoryList(storage.getCategoryModel());
        recyclerView.setAdapter(categoryAdapter);
    }
}
