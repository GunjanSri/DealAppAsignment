package com.nagarro.dealapplication.api.response;

import android.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Offer extends BaseObservable {
    @SerializedName("offers")
    private List<Category> categories;

    public Offer(List<Category> categories){
        this.categories = categories;
    }
}
