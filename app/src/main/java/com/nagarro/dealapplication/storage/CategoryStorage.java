package com.nagarro.dealapplication.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nagarro.dealapplication.model.Category;
import com.nagarro.dealapplication.model.Coupon;
import com.nagarro.dealapplication.model.SingleCategory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CategoryStorage extends Storage{
    private final String STORAGE_ID = "offer_storage";
    private final String CATEGORIES_KEY = "categories_key";
    private final String APPLICATION_STATE_KEY = "application_state_key";
    private final Gson gson = new Gson();
    private static CategoryStorage instance;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public CategoryStorage(Context context){
        super(context);
    }

    @Override
    public String getStorageId() {
        return "category_list";
    }

    public void saveCategories(Map<String , SingleCategory> categories){
        String json = gson.toJson(categories);
        putString(CATEGORIES_KEY , json);
    }

    public Map<String , SingleCategory> getCategories(){
        String json = getString(CATEGORIES_KEY , null);
        Map<String , SingleCategory> categories = gson.fromJson(json , new TypeToken<Map<String , SingleCategory>>(){}.getType());
        return categories;
    }

    public List<Category> getCategoryModel() {
        List<Category> categoryList = new ArrayList<>();
        Map<String, SingleCategory> categoryMap = getCategories();
        Iterator entries = categoryMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            SingleCategory singleCategory = (SingleCategory) entry.getValue();
            String icon = singleCategory.getIcon();
            categoryList.add(new Category(key, icon));
        }
        return categoryList;
    }

    public List<Coupon> getCouponModel(String categoryName){
        List<Coupon> couponList = null;
        Map<String, SingleCategory> categoryMap = getCategories();
        if(categoryMap.containsKey(categoryName)){
            couponList = categoryMap.get(categoryName).getCouponList();
        }
        return couponList;
    }
}
