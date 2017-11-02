package com.example.dreeki.projectleerlingenapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreeki.projectleerlingenapp.R;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("com.example.dreeki.projectleerlingenapp", MODE_PRIVATE);

    }

    @Override
    protected void onResume(){
        super.onResume();



        Intent intent = new Intent(this, EersteKeerOpenenActivity.class);
        startActivity(intent);
    }
}
