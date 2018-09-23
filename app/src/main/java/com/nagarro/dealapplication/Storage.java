package com.nagarro.dealapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nagarro.dealapplication.viewmodel.OfferViewModel;

import java.util.List;

public class Storage {
    private final String STORAGE_ID = "offer_storage";
    private final String OFFER_LIST_KEY = "offer_list_key";
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

    public void saveOffers(OfferViewModel offer){
        String json = gson.toJson(offer);
        editor = sharedPreferences.edit();
        editor.putString(OFFER_LIST_KEY , json);
        editor.commit();
    }

    public OfferViewModel getOffers(){
        String json = sharedPreferences.getString(OFFER_LIST_KEY , null);
        OfferViewModel offer = gson.fromJson(json , new TypeToken<List<OfferViewModel>>(){}.getType());
        return offer;
    }
}
