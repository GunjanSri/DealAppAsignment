package com.nagarro.dealapplication;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {

    private LoginActivity loginActivity;

    @Before
    public void setUp(){
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
    }

}