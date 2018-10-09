package com.nagarro.dealapplication.model;

import java.util.List;

public class SingleCategory {

    private String name;
    private List<Coupon> coupons;
    public SingleCategory(){}

    public SingleCategory(String icon, List<Coupon> couponList) {
        this.name = icon;
        this.coupons = couponList;
    }

    public String getIcon() {
        return name;
    }

    public void setIcon(String icon) {
        this.name = icon;
    }

    public List<Coupon> getCouponList() {
        return coupons;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.coupons = couponList;
    }
}
