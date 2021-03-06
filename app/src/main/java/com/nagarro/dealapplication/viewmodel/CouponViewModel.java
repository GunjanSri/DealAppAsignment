package com.nagarro.dealapplication.viewmodel;

import android.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CouponViewModel extends BaseObservable implements Serializable{

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("validity")
    private String validity;
    @SerializedName("code")
    private String code;
    @SerializedName("location")
    private String location;

    public CouponViewModel(String title, String description, String validity, String code, String location) {
        this.title = title;
        this.description = description;
        this.validity = validity;
        this.code = code;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getValidity() {
        return validity;
    }

    public String getCode() {
        return code;
    }

    public String getLocation() {
        return location;
    }
}
