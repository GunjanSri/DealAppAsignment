package com.nagarro.dealapplication.model;

import java.util.List;

public class SubCategory {
    private String name;
    private String icon;
    private List<Coupon> coupons;

    public SubCategory(String name, String icon, List<Coupon> coupons) {
        this.name = name;
        this.icon = icon;
        this.coupons = coupons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
