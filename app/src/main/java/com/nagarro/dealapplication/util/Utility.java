package com.nagarro.dealapplication.util;

import android.content.Context;
import android.widget.Toast;

public class Utility {

    public static boolean validEmailAddress(String emailAddress){
        return  android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress.trim()).matches();
    }

    public static void showToastMessage(Context context , int message){
        Toast.makeText(context , context.getString(message) , Toast.LENGTH_LONG).show();
    }
}
