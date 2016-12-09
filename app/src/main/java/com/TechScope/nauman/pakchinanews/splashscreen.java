package com.TechScope.nauman.pakchinanews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        globalData.splash_flag=false;
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
