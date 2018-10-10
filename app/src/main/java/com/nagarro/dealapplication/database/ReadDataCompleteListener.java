package com.nagarro.dealapplication.database;

import com.nagarro.dealapplication.model.SingleCategory;

import java.util.Map;

public interface ReadDataCompleteListener {
    public void isSuccessful(Map<String , SingleCategory> categoriesMap);
}
