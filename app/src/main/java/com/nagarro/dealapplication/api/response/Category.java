package com.nagarro.dealapplication.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("name")
    private String name;
    @SerializedName("icon")
    private String icon;
    @SerializedName("id")
    private String id;
    @SerializedName("coupons")
    private List<Coupon> coupons;

    public Category(String name , String icon , String id , List<Coupon> coupons){
        this.name = name;
        this.icon = icon;
        this.id = id;
        this.coupons = coupons;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }
}
