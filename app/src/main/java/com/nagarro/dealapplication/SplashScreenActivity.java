package com.nagarro.dealapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.nagarro.dealapplication.api.RestServiceProtocol;
import com.nagarro.dealapplication.api.RetrofitInstance;
import com.nagarro.dealapplication.viewmodel.OfferViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        FirebaseInstanceId.getInstance().getInstanceId();
        RestServiceProtocol serviceProtocol = RetrofitInstance.getRetrofitInstance().
                create(RestServiceProtocol.class);
        Call<OfferViewModel> call = serviceProtocol.getOffers();
        call.enqueue(new Callback<OfferViewModel>() {
            @Override
            public void onResponse(Call<OfferViewModel> call, Response<OfferViewModel> response) {
                generateOfferList(response.body());
            }

            @Override
            public void onFailure(Call<OfferViewModel> call, Throwable t) {
                Toast.makeText(SplashScreenActivity.this, "Something went wrong...Error message: " +
                        t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void generateOfferList(OfferViewModel offers) {
        Storage storage = Storage.getInstance(this);
        storage.saveOffers(offers);

        Intent intent = new Intent(SplashScreenActivity.this , CategoryListActivity.class);
        startActivity(intent);
        SplashScreenActivity.this.finish();
    }
}
