package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String editFeel = sharedPreferences.getString("editKey", "");
        String seekFeel = sharedPreferences.getString("seekKey","");
        String testval = editFeel + " " + seekFeel;
        TextView test = findViewById(R.id.textTest);
        test.setText(testval);
    }
}
