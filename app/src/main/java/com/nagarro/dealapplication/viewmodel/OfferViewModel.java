package com.nagarro.dealapplication.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.nagarro.dealapplication.viewmodel.CategoryTypeViewModel;

import java.util.List;

public class OfferViewModel extends BaseObservable {
    @SerializedName("offers")
    private List<CategoryTypeViewModel> categories;

    public OfferViewModel(List<CategoryTypeViewModel> categories){
        this.categories = categories;
    }

    @Bindable
    public List<CategoryTypeViewModel> getCategories(){
        return categories;
    }
}
