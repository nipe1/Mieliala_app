package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    EditText editFeel;
    SeekBar seekFeel;
    String todayString;
    String colorFeel;
    public static final String editKey = "editKey";
    public static final String seekKey = "seekKey";
    public static final String dateKey = "dateKey";
    public static final String colorKey = "colorKey";
    //väliaikainen intent id
    int ADD_NEW_PART_INTENT_ID = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekFeel = findViewById(R.id.seekFeeling);
        editFeel = findViewById(R.id.editFeel);

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        todayString = formatter.format(todayDate);
    }


    private void goToIntent()
    {
        //siirrytään CalendarActivity -näkymään
        Intent calendarActivityIntent = new Intent(this, CalendarActivity.class);
        startActivityForResult(calendarActivityIntent, ADD_NEW_PART_INTENT_ID);
    }

    public void buttonNext(View v)
    {
        colorPicker();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String c = editFeel.getText().toString();
        int n = seekFeel.getProgress();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(editKey, c);
        editor.putInt(seekKey, n);
        editor.putString(dateKey, todayString);
        editor.putString(colorKey, colorFeel);
        editor.apply();
        goToIntent();
    }

    public void buttonSkip(View v)
    {
        goToIntent();
    }


    public void colorPicker()
    {
        int progress = seekFeel.getProgress();

        if (progress < 20)
        {
            colorFeel = "#534666";
        }
        else if (progress >= 20 && progress < 40)
        {
            colorFeel = "#A4666E";
        }
        else if (progress >= 40 && progress < 60)
        {
            colorFeel = "#CD7672";
        }
        else if (progress >= 60 && progress < 80)
        {
            colorFeel = "#D88B6E";
        }
        else if (progress >= 80)
        {
            colorFeel = "#EEB462";
        }
    }
}