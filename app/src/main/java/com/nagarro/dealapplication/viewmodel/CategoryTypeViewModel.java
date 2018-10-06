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
    private List<CouponViewModel> coupons;

    public CategoryTypeViewModel(String name , String icon , List<CouponViewModel> couponViewModels){
        this.name = name;
        this.icon = icon;
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
    public List<CouponViewModel> getCoupons() {
        return coupons;
    }

}
