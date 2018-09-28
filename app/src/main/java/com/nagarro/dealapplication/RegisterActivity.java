package com.nagarro.dealapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nagarro.dealapplication.databinding.ActivityRegisterBinding;
import com.nagarro.dealapplication.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_register);
        RegisterViewModel viewModel = new RegisterViewModel(this);
        binding.setRegisterViewModel(viewModel);
    }
}
