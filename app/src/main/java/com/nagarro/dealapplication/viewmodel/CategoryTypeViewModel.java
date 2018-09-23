package com.nagarro.dealapplication.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryTypeViewModel extends BaseObservable {

    @SerializedName("name")
    private String name;
    @SerializedName("icon")
    private String icon;
    @SerializedName("id")
    private String id;
    @SerializedName("coupons")
    private List<CouponViewModel> coupons;

    public CategoryTypeViewModel(String name , String icon , String id , List<CouponViewModel> couponViewModels){
        this.name = name;
        this.icon = icon;
        this.id = id;
        this.coupons = couponViewModels;
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public String getIcon() {
        return icon;
    }

    @Bindable
    public String getId() {
        return id;
    }

    @Bindable
    public List<CouponViewModel> getCoupons() {
        return coupons;
    }

   /* private String categoryName;
    private String categoryIon;

    @Bindable
    public String getCategoryName() {
        return categoryName;
    }

    @Bindable
    public String getCategoryIon() {
        return categoryIon;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryIon(String categoryIon) {
        this.categoryIon = categoryIon;
    }*/
}
