package com.nagarro.dealapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    }
}
