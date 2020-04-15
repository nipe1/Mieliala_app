package com.example.mieliala_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.mieliala_app.MainActivity.dateKey;
import static com.example.mieliala_app.MainActivity.editKey;
import static com.example.mieliala_app.MainActivity.seekKey;

public class DayActivity extends AppCompatActivity {

    int averageMood;
    int moodInteger;
    String date; //dd/MM/yyyy
    String notes;
    String dateKeyToString;
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

        setMood();


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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        dateKeyToString = sharedPreferences.getString(dateKey, "");
        //dateTextView.setText("i"+date + " " + dateKeyToString+"i");
        if(date.equals(dateKeyToString)) {
            notes = sharedPreferences.getString(editKey, "");
            noteTextView.setText(notes);
        }

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

    public void setMood() {
        //päivän aikana annetut moodit/mielialat lisätään int listaan (esim. 1 = huono, 2 = menettelee, 3 = hyvä)
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        dateKeyToString = sharedPreferences.getString(dateKey, "");
        
        if(date.equals(dateKeyToString))
        {
            moodInteger = sharedPreferences.getInt(seekKey, 0b1);

            TextView myMoodTextView = findViewById(R.id.myMoodTextView);
            ImageView moodImageView = findViewById(R.id.dayMoodImageView);

            if (moodInteger < 20) {
                //asetetaan dayMoodImageViewille surunaama
                myMoodTextView.setText("Very Bad");
                moodImageView.setImageResource(R.drawable.face1);
            } else if (moodInteger >= 20 && moodInteger < 40) {
                myMoodTextView.setText("Bad");
                moodImageView.setImageResource(R.drawable.face2);
            } else if (moodInteger >= 40 && moodInteger < 60) {
                myMoodTextView.setText("Neutral");
                moodImageView.setImageResource(R.drawable.face3);
            } else if (moodInteger >= 60 && moodInteger < 80) {
                myMoodTextView.setText("Good");
                moodImageView.setImageResource(R.drawable.face4);
            } else if (moodInteger >= 80) {
                myMoodTextView.setText("Excellent");
                moodImageView.setImageResource(R.drawable.face5);
            }
        }
    }

}
