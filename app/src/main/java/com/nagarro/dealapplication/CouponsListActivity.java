package com.nagarro.dealapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nagarro.dealapplication.adapter.CouponListAdapter;
import com.nagarro.dealapplication.model.Category;
import com.nagarro.dealapplication.model.CategoryName;
import com.nagarro.dealapplication.model.Coupon;
import com.nagarro.dealapplication.storage.CategoryStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponsListActivity extends AppCompatActivity {

    private static final String SELECTED_CATEGORY_NAME = "selected_category_name";
    private String selectedCategory ;
    private CategoryStorage storage;
    CouponListAdapter couponsAdapter;
    List<Coupon> couponList;

    @BindView(R.id.foodLayout)
    LinearLayout foodLayout;
    @BindView(R.id.movieLayout)
    LinearLayout movieLayout;
    @BindView(R.id.movieTextView)
    TextView movieTextView;
    @BindView(R.id.foodTextView)
    TextView foodTextView;
    @BindView(R.id.noCouponText)
    TextView noCouponText;
    @BindView(R.id.offerListView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        ButterKnife.bind(this);

        storage = new CategoryStorage(this);
        Bundle bundle = getIntent().getExtras();
        couponsAdapter = new CouponListAdapter(this);
        if(bundle != null){
            selectedCategory = bundle.getString(SELECTED_CATEGORY_NAME);
        }
        if(selectedCategory != null){
            if(selectedCategory.equals(CategoryName.Food.name())){
                foodTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            }else{
                movieTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            couponList = storage.getCouponModel(selectedCategory);
        }
        if(couponList != null && couponList.size() > 0){
            setVisibilty(View.VISIBLE , View.GONE);
            couponsAdapter.setCouponList(couponList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(couponsAdapter);
        }
        else{
            setVisibilty(View.GONE , View.VISIBLE);
        }
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

    @OnClick(R.id.foodLayout)
    public void foodChoosen(){
        foodTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        movieTextView.setTextColor(getResources().getColor(R.color.colorDarkGrey));
        notifyAdapter(CategoryName.Food.name());
    }

    @OnClick(R.id.movieLayout)
    public void movieChoosen(){
        movieTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        foodTextView.setTextColor(getResources().getColor(R.color.colorDarkGrey));
        notifyAdapter(CategoryName.Movie.name());
    }

    private void notifyAdapter(String categoryName){
        List<Coupon> coupons = storage.getCouponModel(categoryName);
        if(coupons != null && coupons.size()>0) {
            setVisibilty(View.VISIBLE , View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(couponsAdapter);
            couponsAdapter.setCouponList(storage.getCouponModel(categoryName));
            couponsAdapter.notifyDataSetChanged();
        }else{
            setVisibilty(View.GONE , View.VISIBLE);
        }
    }

    private void setVisibilty(int recyclerViewVisibilty , int textVisibility){
        recyclerView.setVisibility(recyclerViewVisibilty);
        noCouponText.setVisibility(textVisibility);
    }
}
