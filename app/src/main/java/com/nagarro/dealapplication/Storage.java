package com.nagarro.dealapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nagarro.dealapplication.model.Category;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String STORAGE_ID = "offer_storage";
    private final String DEALS_KEY = "deals_key";
    private final Gson gson = new Gson();
    private static Storage instance;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    private Storage(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(STORAGE_ID , Context.MODE_PRIVATE);
    }

    public static Storage getInstance(final Context context){
        if(instance == null){
            instance = new Storage(context.getApplicationContext());
        }
        return instance;
    }

    public void saveOffers(ArrayList<Category> deals){
        String json = gson.toJson(deals);
        editor = sharedPreferences.edit();
        editor.putString(DEALS_KEY , json);
        editor.commit();
    }

    public ArrayList<Category> getOffers(){
        String json = sharedPreferences.getString(DEALS_KEY , null);
        ArrayList<Category> deals = gson.fromJson(json , new TypeToken<List<Category>>(){}.getType());
        return deals;
    }
}
