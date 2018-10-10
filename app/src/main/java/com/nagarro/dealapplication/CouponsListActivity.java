package com.nagarro.dealapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nagarro.dealapplication.adapter.CouponListAdapter;
import com.nagarro.dealapplication.model.Category;
import com.nagarro.dealapplication.model.CategoryName;
import com.nagarro.dealapplication.model.Coupon;
import com.nagarro.dealapplication.model.SingleCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponsListActivity extends AppCompatActivity {

    private static final String SELECTED_CATEGORY_NAME = "selected_category_name";
    private String selectedCategory ;
    private Storage storage;
    CouponListAdapter couponsAdapter;
    List<Coupon> couponList;

    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.foodLayout)
    LinearLayout foodLayout;
    @BindView(R.id.movieLayout)
    LinearLayout movieLayout;
    @BindView(R.id.movieTextView)
    TextView movieTextView;
    @BindView(R.id.foodTextView)
    TextView foodTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        ButterKnife.bind(this);

        storage = Storage.getInstance(this);
        Bundle bundle = getIntent().getExtras();
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


        RecyclerView recyclerView = findViewById(R.id.offerListView);
        couponsAdapter = new CouponListAdapter(this);
        couponsAdapter.setCouponList(couponList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(couponsAdapter);

        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                List<Category> categories = storeDealsInPreference();
                for(Category category : categories){
                    if(query.contains(category.getName())){
                        couponsAdapter.setCouponList(category.getCoupons());
                        couponsAdapter.notifyDataSetChanged();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        menu.findItem(R.id.menu_about).setIcon(R.drawable.asterix_about_white);
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.foodLayout)
    public void foodChoosen(){
        foodTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        movieTextView.setTextColor(getResources().getColor(R.color.colorDarkGrey));
        couponsAdapter.setCouponList(storage.getCouponModel(CategoryName.Food.name()));
        couponsAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.movieLayout)
    public void movieChoosen(){
        movieTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        foodTextView.setTextColor(getResources().getColor(R.color.colorDarkGrey));
        couponsAdapter.setCouponList(storage.getCouponModel(CategoryName.Movie.name()));
        couponsAdapter.notifyDataSetChanged();
    }
}
