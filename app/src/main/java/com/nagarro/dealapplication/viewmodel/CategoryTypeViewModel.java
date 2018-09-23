package com.nagarro.dealapplication.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class CategoryTypeViewModel extends BaseObservable {

    private String categoryName;
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
    }
}
