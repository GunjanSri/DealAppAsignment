package com.nagarro.dealapplication.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nagarro.dealapplication.model.Category;
import com.nagarro.dealapplication.model.Coupon;
import com.nagarro.dealapplication.model.SingleCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CategoryStorage extends Storage {
    private final String STORAGE_ID = "offer_storage";
    private final String CATEGORIES_KEY = "categories_key";
    private final String APPLICATION_STATE_KEY = "application_state_key";
    private final String SELECTED_LOCATION_KEY = "selected_location_key";
    private final String LOCATION_DIALG_SHOWN = "location_dialog_shown";
    private final Gson gson = new Gson();

    public CategoryStorage(Context context) {
        super(context);
    }

    @Override
    public String getStorageId() {
        return "category_list";
    }

    public boolean isLocationShown(){
        return getBoolean(LOCATION_DIALG_SHOWN , false);
    }

    public void setLocationShown(boolean locationShown){
        putBoolean(LOCATION_DIALG_SHOWN , locationShown);
    }

    public void saveCategories(Map<String, SingleCategory> categories) {
        String json = gson.toJson(categories);
        putString(CATEGORIES_KEY, json);
    }

    public Map<String, SingleCategory> getCategories() {
        String json = getString(CATEGORIES_KEY, null);
        Map<String, SingleCategory> categories = gson.fromJson(json, new TypeToken<Map<String, SingleCategory>>() {
        }.getType());
        return categories;
    }

    public void saveLocation(String location){
        putString(SELECTED_LOCATION_KEY , location);
    }

    public String getLocation(){
        return getString(SELECTED_LOCATION_KEY , null);
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

    public List<Coupon> getCouponModel(String categoryName) {
        List<Coupon> couponList = null;
        Map<String, SingleCategory> categoryMap = null;
        if(getLocation() != null){
            categoryMap = getCategoryBasedOnLocation(getLocation());
        }else{
            categoryMap = getCategories();
        }
        if (categoryMap.containsKey(categoryName)) {
            couponList = categoryMap.get(categoryName).getCouponList();
        }
        return couponList;
    }

    public Map<String, SingleCategory> getCategoryBasedOnLocation(String location) {
        Map<String, SingleCategory> resultMap = new HashMap<>();
        Map<String, SingleCategory> categoryMap = getCategories();
        Iterator entries = categoryMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            SingleCategory singleCategory = (SingleCategory) entry.getValue();
            for (Coupon coupon : singleCategory.getCouponList()) {
                if (coupon.getLocation().equalsIgnoreCase(location)) {
                    if (resultMap.containsKey(key)) {
                        resultMap.get(key).getCouponList().add(coupon);
                    } else {
                        List<Coupon> coupons = new ArrayList<>();
                        coupons.add(coupon);
                        resultMap.put(key, new SingleCategory(singleCategory.getIcon(), coupons));
                    }
                }
            }

        }

        return resultMap;
    }
}
