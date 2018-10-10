package com.nagarro.dealapplication;

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

public class Storage {
    private final String STORAGE_ID = "offer_storage";
    private final String CATEGORIES_KEY = "categories_key";
    private final Gson gson = new Gson();
    private static Storage instance;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    private Storage(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(STORAGE_ID , Context.MODE_PRIVATE);
    }

    public static Storage getInstance(final Context context){
        if(instance == null){
            instance = new Storage(context.getApplicationContext());
        }
        return instance;
    }

    public void saveCategories(Map<String , SingleCategory> categories){
        String json = gson.toJson(categories);
        editor = sharedPreferences.edit();
        editor.putString(CATEGORIES_KEY , json);
        editor.commit();
    }

    public Map<String , SingleCategory> getCategories(){
        String json = sharedPreferences.getString(CATEGORIES_KEY , null);
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
