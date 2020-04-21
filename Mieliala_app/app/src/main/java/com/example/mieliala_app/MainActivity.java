package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    EditText editFeel;
    SeekBar seekFeel;
    String todayString;
    int colorFeel;
    public static final String editKey = "editKey";
    public static final String seekKey = "seekKey";
    public static final String dateKey = "dateKey";
    public static final String colorKey = "colorKey";
    //väliaikainen intent id
    int ADD_NEW_PART_INTENT_ID = 1234;

    //ArrayList<String> noteList;
    //ArrayList<Integer> seekList;
    //ArrayList<String> dateList;
    //ArrayList<String> colorList;
    //private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekFeel = findViewById(R.id.seekFeeling);
        editFeel = findViewById(R.id.editFeel);


        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        todayString = formatter.format(todayDate);

        //noteList = new ArrayList<>();
        //seekList = new ArrayList<>();
        //dateList = new ArrayList<>();
        //colorList = new ArrayList<>();
    }


    private void goToIntent()
    {
        //siirrytään CalendarActivity -näkymään
        Intent calendarActivityIntent = new Intent(this, CalendarActivity.class);
        startActivityForResult(calendarActivityIntent, ADD_NEW_PART_INTENT_ID);
    }

    public void buttonNext(View v) throws IOException {
        colorPicker();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String c = editFeel.getText().toString();
        int n = seekFeel.getProgress();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(editKey, c);
        editor.putInt(seekKey, n);
        editor.putString(dateKey, todayString);
        editor.putInt(colorKey, colorFeel);

        //noteList.add(c);
        //sharedPreferences.edit().putString(editKey,ObjectSerializer.serialize(noteList)).apply();
        //seekList.add(n);
        //sharedPreferences.edit().putInt(seekKey,ObjectSerializer.serializeInt(seekList)).apply();
        //dateList.add(todayString);
        //sharedPreferences.edit().putString(dateKey, ObjectSerializer.serialize(dateList)).apply();
        //colorList.add(colorFeel);
        //sharedPreferences.edit().putString(colorKey, ObjectSerializer.serialize(colorList)).apply();


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
            colorFeel = Color.parseColor("#534666");
        }
        else if (progress >= 20 && progress < 40)
        {
            colorFeel = Color.parseColor("#A4666E");
        }
        else if (progress >= 40 && progress < 60)
        {
            colorFeel = Color.parseColor("#CD7672");
        }
        else if (progress >= 60 && progress < 80)
        {
            colorFeel = Color.parseColor("#D88B6E");
        }
        else if (progress >= 80)
        {
            colorFeel = Color.parseColor("#EEB462");
        }
    }
}