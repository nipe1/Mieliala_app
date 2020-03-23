package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    //väliaikainen intent id, jolla hypätään suoraan DayActivity -näkymään
    int ADD_NEW_PART_INTENT_ID = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //siirrytään suoraan DayActivity -näkymään
        Intent dayActivityIntent = new Intent(this, DayActivity.class);
        startActivityForResult(dayActivityIntent, ADD_NEW_PART_INTENT_ID);

    }
}
