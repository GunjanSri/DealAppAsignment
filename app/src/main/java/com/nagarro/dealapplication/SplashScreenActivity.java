package com.nagarro.dealapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.nagarro.dealapplication.api.RestServiceProtocol;
import com.nagarro.dealapplication.api.RetrofitInstance;
import com.nagarro.dealapplication.database.MyFirebaseDatabase;
import com.nagarro.dealapplication.viewmodel.OfferViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity{

    @BindView(R.id.versionText)
    TextView versionText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        versionText.setText(String.format(getString(R.string.app_version) , BuildConfig.VERSION_NAME));

        FirebaseInstanceId.getInstance().getInstanceId();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this , LoginActivity.class);
                startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        } , 5000);
       /* RestServiceProtocol serviceProtocol = RetrofitInstance.getRetrofitInstance().
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
        });*/
    }

    private void generateOfferList(OfferViewModel offers) {
        Storage storage = Storage.getInstance(this);
        storage.saveOffers(offers);

        MyFirebaseDatabase db = new MyFirebaseDatabase();
        db.getFirebaseDbInstance(this);
        db.writeDataToDb();
        db.readFromDb();

       /* Intent intent = new Intent(SplashScreenActivity.this , CategoryListActivity.class);
        startActivity(intent);
        SplashScreenActivity.this.finish();*/
    }
}
