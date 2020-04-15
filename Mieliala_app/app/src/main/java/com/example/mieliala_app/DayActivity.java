package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.mieliala_app.MainActivity.dateKey;
import static com.example.mieliala_app.MainActivity.editKey;

public class DayActivity extends AppCompatActivity {

    int averageMood;
    String date; //dd/MM/yyyy
    String notes;
    String dateEditKey;
    //final public static String editKey = "editKey";
    //final public static String seekKey = "seekKey";

    SharedPreferences sharedPreferences;

    List<Integer> dayMoodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        date = "dd/MM/yyyy"; //asetetaan arvoksi kalenterista valitun päivän päivämäärä
        Intent myIntent = getIntent();
        date = myIntent.getExtras().getString("choosedDay");

        TextView dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setText(date);
        TextView noteTextView = findViewById(R.id.noteTextView);





        //Haetaan kyseisen päivän mielialat ja lisätään ne listDayM
        /*for (int i = 0; i < moodList.size(); i++)
        {
            if(date == moodList(i).date)
            {
                dayMoodList.add(moodList(i).mood);
            }
        }*/

        //getAverageMood();
        //setMood();

        /*for (int i = 0; i < noteList.size(); i++)
        {
            if(date == noteList(i).date)
            {
                notes = notes + "\n\n" + noteList(i).note;
            }
        }*/

        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (dateKey == date)
        {
            noteTextView.setText(getPref(editKey, getApplicationContext()));
        }

        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //notes = sharedPreferences.getString(editKey, "");
        //noteTextView.setText(notes);

    }

    public static String getPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public void getAverageMood()
    {
        //Lasketaan päivän mielialoista keskiarvo
        int total = 0;
        for(int i = 0; i < dayMoodList.size(); i++)
        {
            total += dayMoodList.get(i);
            averageMood = total / dayMoodList.size();
        }
        averageMood = 3; //väliaikainen arvo
    }

    public void setMood()
    {
        //päivän aikana annetut moodit/mielialat lisätään int listaan (esim. 1 = huono, 2 = menettelee, 3 = hyvä)
        TextView myMoodTextView = findViewById(R.id.myMoodTextView);

        if(averageMood == 1)
        {
            //asetetaan dayMoodImageViewille surunaama
            myMoodTextView.setText("Bad");
        }

        if(averageMood == 2)
        {
            //asetetaan dayMoodImageViewille neutraali naama
            myMoodTextView.setText("Neutral");
        }

        if(averageMood == 3)
        {
            //asetetaan dayMoodImageViewille hymynaama
            myMoodTextView.setText("Good");
        }
    }

}
