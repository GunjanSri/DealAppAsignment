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
import com.nagarro.dealapplication.model.Coupon;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponsListActivity extends AppCompatActivity {

    private static final String SELECTED_CATEGORY = "selected_category";
    private int couponCategoryPosition = -1;
    private Storage storage;
    CouponListAdapter couponsAdapter;

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
            couponCategoryPosition = bundle.getInt(SELECTED_CATEGORY);
        }
        if(couponCategoryPosition == 0){
            foodTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        }else{
            movieTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        RecyclerView recyclerView = findViewById(R.id.offerListView);
        couponsAdapter = new CouponListAdapter(this);
        couponsAdapter.setCouponList(storeDealsInPreference().get(0).getCoupons());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(couponsAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

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
        });
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
        couponsAdapter.setCouponList(storeDealsInPreference().get(0).getCoupons());
        couponsAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.movieLayout)
    public void movieChoosen(){
        movieTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        foodTextView.setTextColor(getResources().getColor(R.color.colorDarkGrey));
        couponsAdapter.setCouponList(storeDealsInPreference().get(2).getCoupons());
        couponsAdapter.notifyDataSetChanged();
    }

    private List<Category> storeDealsInPreference(){

        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon("Swiggy" , "dsgdkshldshlhlsdcdcdd" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/swiggy.png?alt=media&token=4cd91ddf-e016-4f40-9280-a5fe0814e4c4",
                "Gurgaon" , "31-Oct-2018" , "CODE-001"));
        ArrayList<Coupon> coupons2 = new ArrayList<>();
        coupons2.add(new Coupon("Swigdjj" , "dsgdkshldshlhls" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/swiggy.png?alt=media&token=4cd91ddf-e016-4f40-9280-a5fe0814e4c4",
                "Gurgaon" , "31-Oct-2018" , "CODE-004"));
        categories.add(new Category("food" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/food.png?alt=media&token=ad4778f8-757c-4dd5-baca-7ffd533f0fc8",
                coupons));

        categories.add(new Category("food" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/food.png?alt=media&token=ad4778f8-757c-4dd5-baca-7ffd533f0fc8",
                coupons));

        categories.add(new Category("movies" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/food.png?alt=media&token=ad4778f8-757c-4dd5-baca-7ffd533f0fc8",
                coupons2));

        categories.add(new Category("movies" , "https://firebasestorage.googleapis.com/v0/b/testprojects-d2801.appspot.com/o/food.png?alt=media&token=ad4778f8-757c-4dd5-baca-7ffd533f0fc8",
                coupons2));

        storage.saveOffers(categories);
        return categories;
    }
}
