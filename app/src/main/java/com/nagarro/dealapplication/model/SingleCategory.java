package com.nagarro.dealapplication.model;

import java.util.List;

public class SingleCategory {

    private String icon;
    private List<Coupon> coupons;

    public SingleCategory(String icon, List<Coupon> couponList) {
        this.icon = icon;
        this.coupons = couponList;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Coupon> getCouponList() {
        return coupons;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.coupons = couponList;
    }
}
