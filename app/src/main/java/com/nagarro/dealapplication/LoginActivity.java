package com.nagarro.dealapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nagarro.dealapplication.databinding.ActivityLoginBinding;
import com.nagarro.dealapplication.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        LoginViewModel viewModel = new LoginViewModel(this);
        binding.setLoginViewModel(viewModel);

    }
}
