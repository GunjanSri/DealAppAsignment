package com.nagarro.dealapplication.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.List;

public class Category extends BaseObservable{
    private String name;
    private String icon;
    private List<Coupon> coupons;

    public Category(String name, String icon, List<Coupon> coupons) {
        this.name = name;
        this.icon = icon;
        this.coupons = coupons;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Bindable
    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
